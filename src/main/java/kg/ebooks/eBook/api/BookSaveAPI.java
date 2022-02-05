package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 11:09
 * hello world
 */
@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookSaveAPI {

    private final BookService bookService;

    @GetMapping
    public Set<BookDTO> getAllBooks() {
        return bookService.findALLBooks();
    }

    @PostMapping("save/audio_book")
    public BookDTO saveAudioBook(BookSave<AudioBook> audioBook) {
        return bookService.saveAudioBook(audioBook);
    }

    @PostMapping("save/electronic_book")
    public BookDTO saveElectronicBook(BookSave<ElectronicBook> electronicBook) {
        return null;//bookService.saveElectronicBook(electronicBook);
    }

    @PostMapping("save/paper_book")
    public BookDTO savePaperBook(BookSave<PaperBook> paperBook) {
        return null;//bookService.savePaperBook(paperBook);
    }

}
