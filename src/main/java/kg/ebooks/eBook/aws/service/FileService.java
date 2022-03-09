package kg.ebooks.eBook.aws.service;

import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 03:05
 */
public interface FileService {

    FileInfo uploadFile(FolderName folderName, MultipartFile multipartFile);

    byte[] downloadFile(Long fileId);

    void deleteFile(Long fileId);

    FileInfo findById(Long id);

    void clean();

}
