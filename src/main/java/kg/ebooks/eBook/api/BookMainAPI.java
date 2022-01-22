package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.main_card.BookInfo1;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfoForAudio;
import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.service.BookService;
import kg.ebooks.eBook.exceptions.InvalidChapterException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 21/1/22
 * Friday 12:48
 */
@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookMainAPI {

    private final BookService bookService;

    @GetMapping("/main/{chapter}")
    public List<BookInfo1> getBooks(@PathVariable String chapter) {
        return switch (chapter) {
            case "latest" -> bookService.getLatestBooks();
            case "electronic-book" -> bookService.getAllElectronicBooks();
            case "bestseller" -> bookService.getAllBestsellerBooks();
            default -> throw new InvalidChapterException(
                    "you give wrong chapter"
            );
        };
    }

    @GetMapping("/main/audio-book")
    public List<BookInfoForAudio> getAudioBooks() {
        return bookService.getAllAudioBooks();
    }

    @GetMapping("/inner-page/{bookId}")
    public BookInfo getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }
}
