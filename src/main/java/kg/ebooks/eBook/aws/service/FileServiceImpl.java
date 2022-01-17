package kg.ebooks.eBook.aws.service;

import com.amazonaws.AmazonServiceException;
import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.repository.FileRepository;
import kg.ebooks.eBook.aws.working_with_files.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 03:45
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileStore fileStore;

    @Override
    public FileInfo uploadFile(FolderName folderName, MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) {
            log.error("file must not be null {}", multipartFile.getSize());
            throw new IllegalStateException(
                    "cannot upload empty file [ " + multipartFile.getSize() + " ]"
            );
        }

        if (!getMimeTypes(folderName).contains(multipartFile.getContentType())) {
            log.error("file should be like {} this files", getMimeTypes(folderName));
            throw new IllegalStateException(
                    "file should be an " + folderName.name()
            );
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));

        String fileName = String.format("%s/%s", UUID.randomUUID(), multipartFile.getOriginalFilename());
        try {
            fileStore.save(folderName.getPath(), fileName, Optional.of(metadata), multipartFile.getInputStream());
            log.info("successfully upload file to amazon s3 server :: file name = {}", fileName);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException(
                    "failed to save file to Amazon s3 server"
            );
        }
        return fileRepository.save(new FileInfo(null, folderName, fileName));
    }

    private Set<String> getMimeTypes(FolderName folderName) {
        return folderName.getMimeTypes(folderName.getContentTypes());
    }

    @Override
    public byte[] downloadFile(Long fileId) {
        FileInfo fileInfo = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalStateException(
                        "CANNOT DOWNLOAD FILE \n reason: file does not exists"
                ));
        log.info("downloading file [{}]", fileInfo.getFileName());
        return fileStore.download(fileInfo.getFolderName().getPath(), fileInfo.getFileName());
    }

    @Override
    public void deleteFile(Long fileId) {
        FileInfo fileInfo = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalStateException(
                        "file with id = " + fileId + " does not exists"
                ));
        try {
            fileStore.delete(fileInfo.getFolderName().getPath(), fileInfo.getFileName());
            log.info("successfully deleted from to database :: file name = {}", fileInfo.getFileName());
        } catch (AmazonServiceException e) {
            log.info("failed to delete file from amazon s3 bucket");
            throw new IllegalStateException(
                    "failed to delete file from amazon s3 bucket"
            );
        }
    }
}
