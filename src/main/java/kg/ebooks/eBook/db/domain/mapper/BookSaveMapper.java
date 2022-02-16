package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Book makeBookFromBookRequest(TypeOfBook typeOfBook, BookSave<? extends BookRequest> bookSave) {

        Set<FileInfo> fileInfos = bookSave.getImages().stream()
                .map(fileId -> {
                    FileInfo fileInfoById = fileService.findById(fileId);
                    if (!fileInfoById.isFree()) {
                        log.error("file with id = [{}] has already in a book", fileId);
                        throw new InvalidRequestException(
                                String.format("file with id = [%d] has already in a book", fileId)
                        );
                    }
                    return fileInfoById;
                }).collect(Collectors.toSet());


        Book book = makeABook(fileInfos,
                bookSave.getBookName(),
                bookSave.getAuthor(),
                bookSave.getDescription(),
                bookSave.getLanguage(),
                bookSave.getDataOfIssue(),
                bookSave.getBestSeller(),
                bookSave.getPrice(),
                bookSave.getDiscount());

        switch (typeOfBook) {
            case PAPER_BOOK:
                PaperBookRequest paperBookRequest = (PaperBookRequest) bookSave.getBook();
                PaperBook paperBook = new PaperBook();
                paperBook.setFragment(paperBookRequest.getFragment());
                paperBook.setPublishingHouse(paperBookRequest.getPublishingHouse());
                paperBook.setPageSize(paperBook.getPageSize());
                paperBook.setQuantityOfBooks(paperBook.getQuantityOfBooks());
                book.setPaperBook(paperBook);
                book.setTypeOfBook(PAPER_BOOK);
                break;
            case AUDIO_BOOK:
                AudioBookRequest audioBookRequest = (AudioBookRequest) bookSave.getBook();
                AudioBook audioBook = new AudioBook();
                audioBook.setFragment(fileService.findById(audioBookRequest.getFragmentId()));
                audioBook.setDuration(audioBookRequest.getDuration().makeLocalTime());
                audioBook.setAudioBook(fileService.findById(audioBookRequest.getAudioBookId()));
                book.setAudioBook(audioBook);
                book.setTypeOfBook(AUDIO_BOOK);
                break;
            case ELECTRONIC_BOOK:
                ElectronicBookRequest electronicBookRequest = (ElectronicBookRequest) bookSave.getBook();
                ElectronicBook electronicBook = new ElectronicBook();
                electronicBook.setFragment(electronicBookRequest.getFragment());
                electronicBook.setPublishingHouse(electronicBookRequest.getPublishingHouse());
                electronicBook.setPageSize(electronicBookRequest.getPageSize());
                electronicBook.setElectronicBook(fileService.findById(electronicBookRequest.getElectronicBookId()));
                book.setElectronicBook(electronicBook);
                book.setTypeOfBook(ELECTRONIC_BOOK);
                break;
        }
        return setGenreToBook(book, bookSave.getGenreId());
    }


    @Transactional
    Book setGenreToBook(Book book, Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "genre with id = " + genreId + " does not exists"
                ));

        book.setGenre(genre);
        genre.setBook(book);
        genre.count();
        return book;
    }
}
