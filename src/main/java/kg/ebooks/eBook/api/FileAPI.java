package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Tag(name = "This API for saving files {images, audios, electronic_books} to amazon S3 bucket")
@PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
public class FileAPI {

    private final FileService fileService;

    @PostMapping("/upload/image")
    public FileInfo uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(FolderName.IMAGES, file);
    }

    @PostMapping("/upload/audio")
    public FileInfo uploadAudioFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(FolderName.AUDIO_FILES, file);
    }

    @PostMapping("/upload/pdf")
    public FileInfo uploadPDFFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(FolderName.PDF_FILES, file);
    }

    @PostMapping("/upload/audio/fragment")
    public FileInfo uploadFragmentFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(FolderName.AUDIO_FRAGMENTS_FILES, file);
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
