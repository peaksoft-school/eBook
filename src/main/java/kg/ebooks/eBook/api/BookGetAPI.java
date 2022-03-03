package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.service.BookGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class BookGetAPI {

    private final BookGetService bookGetService;

    @GetMapping("/get/{bookId}")
    public BookInfo getBookById(@PathVariable Long bookId) {
        return bookGetService.getBookById(bookId);
    }

    @GetMapping("/getLikes")
    public List<BookMainPage> bookMainPageList() {
        return bookGetService.getThreeBooks();
    }

    @GetMapping("/getAudio")
    public List<GetAudioBookDto> getAudioBook() {
        return bookGetService.getAudioBook();
    }

    @GetMapping("/getElectronic")
    public List<GetElectronicBookDTO> getElectronicBook() {
        return bookGetService.getElectronicBook();
    }

    @GetMapping("/getBestseller")
    public List<GetBestsellerBookDTO> getBestsellerBook() {
        return bookGetService.getBestsellerBook();
    }

    @GetMapping("/getGenreLastPublication")
    public ResponseEntity<GetGenreLastPostBookDTO> getGenreLastPostBook(@RequestParam("genreId") Long genreId) {

        try {
            return new ResponseEntity<>(bookGetService.getGenreLastPost(genreId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
