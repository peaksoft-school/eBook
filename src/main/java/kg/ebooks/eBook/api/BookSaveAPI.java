package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.book.AudioDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.dto.book.PaperBookSaveDTO;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.service.BookSaveService;
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
@Tag(name = "This API for saving books")
public class BookSaveAPI {

    private final BookSaveService bookService;

    @GetMapping
    public Set<BookDTO> getAllBooks() {
        return bookService.findALLBooks();
    }

    @PostMapping("save/audio_book")
    public BookDTO saveAudioBook(@RequestBody BookSave<AudioDTO> audioBook) {
        return bookService.saveAudioBook(audioBook);
    }

    @PostMapping("save/electronic_book")
    public BookDTO saveElectronicBook(@RequestBody BookSave<ElectronicBook> electronicBook) {
        return null;//bookService.saveElectronicBook(electronicBook);
    }

    @PostMapping("save/paper_book")
    public BookDTO savePaperBook(@RequestBody BookSave<PaperBookSaveDTO> paperBook) {
        return bookService.savePaperBook(paperBook);
    }

}
