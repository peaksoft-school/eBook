package kg.ebooks.eBook.aws.api;

import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 21:00
 */
@RestController
@RequestMapping("/static")
@RequiredArgsConstructor
@Slf4j
public class FileAPI {

    private final FileService fileService;

    @PostMapping("/upload/{typeOfFile}")
    public FileInfo uploadFile(@PathVariable String typeOfFile,
                               @RequestParam MultipartFile file) {
        return switch (typeOfFile) {
            case "image" -> fileService.uploadFile(FolderName.IMAGES, file);
            case "audio" -> fileService.uploadFile(FolderName.AUDIO_FILES, file);
            case "pdf" -> fileService.uploadFile(FolderName.PDF_FILES, file);
            default -> {
                log.error("invalid type of file [{}]", typeOfFile);
                throw new IllegalStateException(
                        "Invalid type of file [ " + typeOfFile + " ]"
                );
            }
        };
    }

    @GetMapping("/download/{fileId}")
    public byte[] downloadFile(@PathVariable Long fileId) {
        return fileService.downloadFile(fileId);
    }

    @DeleteMapping("/delete/{fileId}")
    public void deleteFile(@PathVariable Long fileId) {
        fileService.deleteFile(fileId);
    }

}
