package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.dto.genre.GenreGetDTO;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.GenreService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 2/2/22
 * Wednesday 10:14
 * hello world
 */
@Component
@RequiredArgsConstructor
public class BookSaveMapper {

    private final GenreRepository genreRepository;

    public Book makeBookFromAudioBook(BookSave<AudioBook> audioBook) {
        Book book = makeABook(audioBook.getImages(),
                audioBook.getTypeOfBook(),
                audioBook.getBookName(),
                audioBook.getAuthor(),
                audioBook.getDescription(),
                audioBook.getLanguage(),
                audioBook.getDataOfIssue(),
                audioBook.getBestSeller(),
                audioBook.getPrice(),
                audioBook.getDiscount());

        AudioBook audioBook1 = new AudioBook();
        audioBook1.setFragment(audioBook.getBook().getFragment());
        audioBook1.setDuration(audioBook.getBook().getDuration());
        audioBook1.setAudioBook(audioBook.getBook().getAudioBook());

        book.setAudioBook(audioBook1);
        return book;
    }

    private static Book makeABook(List<FileInfo> images,
                                  TypeOfBook typeOfBook,
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
        book.setTypeOfBook(typeOfBook);
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
        Book book = makeABook(electronicBookToSave.getImages(),
                electronicBookToSave.getTypeOfBook(),
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

    public Book makeBookFromPaperBook(BookSave<PaperBook> paperBookToSave) {
        Book book = makeABook(paperBookToSave.getImages(),
                paperBookToSave.getTypeOfBook(),
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

        return book;
    }

    public Book setGenreToBook(Book book, Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "genre with id = " + genreId + " does not exists"
                ));

        book.setGenre(genre);
        return book;
    }
}
