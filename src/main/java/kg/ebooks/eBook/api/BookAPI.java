package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@CrossOrigin
public class BookAPI {

    private final BookService bookService;

    @PutMapping("/like/{bookId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public String like(Authentication authentication, @PathVariable Long bookId) {
        return bookService.like(authentication.getName(), bookId);
    }

    @PutMapping("/dislike/{bookId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public String dislike(Authentication authentication, @PathVariable Long bookId) {
        return bookService.dislike(authentication.getName(), bookId);
    }
}

