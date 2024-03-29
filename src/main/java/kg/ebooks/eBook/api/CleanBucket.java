package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.aws.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/3/22
 * Tuesday 14:47
 * hello world
 */
@RestController
@Tag(name = "basket cleaner", description = "this is temporary")
@RequiredArgsConstructor
public class CleanBucket {

    private final FileService fileService;

    @DeleteMapping("/bucket/clean")
    public String cleanBucket() {
        fileService.clean();
        return "success";
    }
}
