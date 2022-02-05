package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.domain.dto.book.AudioDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.dto.book.PaperBookSaveDTO;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.TypeOfBook.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 2/2/22
 * Wednesday 10:14
 * hello world
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BookSaveMapper {

    private final GenreRepository genreRepository;
    private final FileService fileService;
    private final BookRepository bookRepository;

    public Book makeBookFromAudioBook(BookSave<AudioDTO> audioBook) {
        Set<FileInfo> fileInfos = audioBook.getImages().stream()
                .map(fileService::findById)
                .collect(Collectors.toSet());

        Book book = makeABook(fileInfos,
                audioBook.getBookName(),
                audioBook.getAuthor(),
                audioBook.getDescription(),
                audioBook.getLanguage(),
                audioBook.getDataOfIssue(),
                audioBook.getBestSeller(),
                audioBook.getPrice(),
                audioBook.getDiscount());

        AudioBook audioBook1 = new AudioBook();
        audioBook1.setFragment(fileService.findById(audioBook.getBook().getFragmentId()));
        audioBook1.setDuration(audioBook.getBook().getDuration().makeLocalTime(audioBook.getBook().getDuration()));
        audioBook1.setAudioBook(fileService.findById(audioBook.getBook().getAudioBookId()));

        book.setAudioBook(audioBook1);
        book.setTypeOfBook(AUDIO_BOOK);
        return setGenreToBook(book, audioBook.getGenreId());
    }

    private static Book makeABook(Set<FileInfo> images,
                                  String bookName,
                                  String author,
                                  String description,
                                  Language language,
                                  LocalDate dataOfIssue,
                                  Boolean bestSeller,
                                  BigDecimal price,
                                  byte discount) {
        Book book = new Book();
        book.setImages(images);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setDescription(description);
        book.setLanguage(language);
        book.setDateOfIssue(dataOfIssue);
        book.setBestSeller(bestSeller);
        book.setPrice(price);
        book.setDiscount(discount);
        return book;
    }

    public Book makeBookFromElectronicBook(BookSave<ElectronicBook> electronicBookToSave) {

        Set<FileInfo> fileInfos = electronicBookToSave.getImages().stream()
                .map(fileService::findById)
                .collect(Collectors.toSet());


        Book book = makeABook(fileInfos,
                electronicBookToSave.getBookName(),
                electronicBookToSave.getAuthor(),
                electronicBookToSave.getDescription(),
                electronicBookToSave.getLanguage(),
                electronicBookToSave.getDataOfIssue(),
                electronicBookToSave.getBestSeller(),
                electronicBookToSave.getPrice(),
                electronicBookToSave.getDiscount());

        ElectronicBook electronicBook = new ElectronicBook();
        electronicBook.setPageSize(electronicBookToSave.getBook().getPageSize());
        electronicBook.setPublishingHouse(electronicBookToSave.getBook().getPublishingHouse());
        electronicBook.setFragment(electronicBookToSave.getBook().getFragment());
        electronicBook.setElectronicBook(electronicBookToSave.getBook().getElectronicBook());

        book.setElectronicBook(electronicBook);

        return book;
    }

    public Book makeBookFromPaperBook(BookSave<PaperBookSaveDTO> paperBookToSave) {
        Set<FileInfo> fileInfos = paperBookToSave.getImages().stream()
                .map(fileService::findById)
                .collect(Collectors.toSet());

        Book book = makeABook(fileInfos,
                paperBookToSave.getBookName(),
                paperBookToSave.getAuthor(),
                paperBookToSave.getDescription(),
                paperBookToSave.getLanguage(),
                paperBookToSave.getDataOfIssue(),
                paperBookToSave.getBestSeller(),
                paperBookToSave.getPrice(),
                paperBookToSave.getDiscount());

        PaperBook paperBook = new PaperBook();
        paperBook.setPageSize(paperBookToSave.getBook().getPageSize());
        paperBook.setPublishingHouse(paperBookToSave.getBook().getPublishingHouse());
        paperBook.setFragment(paperBookToSave.getBook().getFragment());
        paperBook.setQuantityOfBooks(paperBookToSave.getBook().getQuantityOfBooks());

        book.setPaperBook(paperBook);
        book.setTypeOfBook(PAPER_BOOK);
        return setGenreToBook(book, paperBookToSave.getGenreId());
    }

    @Transactional
    Book setGenreToBook(Book book, Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "genre with id = " + genreId + " does not exists"
                ));

        book.setGenre(genre);
        genre.setBook(book);
        return book;
    }
}
