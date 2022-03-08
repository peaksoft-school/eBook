package kg.ebooks.eBook.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.annotations.CurrentUser;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.Type;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.BookSaveService;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import kg.ebooks.eBook.exceptions.NotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

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
@Slf4j
public class BookSaveAPI {

    private final BookSaveService bookService;

    @PostMapping("/save/audio_book")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    public BookResponse saveAudioBook(Authentication authentication,
                                      @Valid @RequestBody BookSave<AudioBookRequest> audioBook) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return bookService.saveBook(authenticationInfo, AUDIO_BOOK, audioBook);
    }

    @PostMapping("/save/electronic_book")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    public BookResponse saveElectronicBook(Authentication authentication,
                                           @Valid @RequestBody BookSave<ElectronicBookRequest> electronicBook) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
            return bookService.saveBook(authenticationInfo, ELECTRONIC_BOOK, electronicBook);
    }

    @PostMapping("/save/paper_book")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    public BookResponse savePaperBook(Authentication authentication,
                                      @Valid @RequestBody BookSave<PaperBookRequest> paperBook) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return bookService.saveBook(authenticationInfo, PAPER_BOOK, paperBook);
    }

    @PutMapping("/update/paper_book/{bookId}")
    public BookResponse updatePaperBook(
            @PathVariable Long bookId,
            @Valid @RequestBody BookSave<PaperBookRequest> bookSave) {
        return bookService.updateBook(bookId, PAPER_BOOK, bookSave);
    }

    @PutMapping("/update/audio_book/{bookId}")
    public BookResponse updateAudioBook(
            @PathVariable Long bookId,
            @Valid @RequestBody BookSave<AudioBookRequest> bookSave) {
        return bookService.updateBook(bookId, AUDIO_BOOK, bookSave);
    }

    @PutMapping("/update/electronic_book/{bookId}")
    public BookResponse updateElectronicBook(
            @PathVariable Long bookId,
            @Valid @RequestBody BookSave<ElectronicBookRequest> bookSave) {
        return bookService.updateBook(bookId, ELECTRONIC_BOOK, bookSave);
    }

    @GetMapping("/languages")
    public Language[] getAllLanguage() {
        return Language.values();
    }

    @GetMapping("/types")
    public TypeOfBook[] getTypesOfBook() {
        return TypeOfBook.values();
    }
}
