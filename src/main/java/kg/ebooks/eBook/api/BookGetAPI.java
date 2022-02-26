package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.service.BookGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:30
 * hello world
 */
@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@Tag(name = "This API to get Books")
@CrossOrigin
public class BookGetAPI {

    private final BookGetService bookGetService;

    @GetMapping("/get/{bookId}")
    public BookInfo getBookById(@PathVariable Long bookId) {
        return bookGetService.getBookById(bookId);
    }

}
