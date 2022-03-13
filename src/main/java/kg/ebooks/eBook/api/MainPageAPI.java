package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.book.BookMainPage;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.main.MainPageRequest;
import kg.ebooks.eBook.db.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/main")
@CrossOrigin
@RequiredArgsConstructor
public class MainPageAPI {

    private final MainPageService mainPageService;

    @GetMapping
    public List<BookMainPage> getBooksToMainPage(@RequestParam MainPageRequest request) {
        return mainPageService.findByRequest(request);
    }
}
