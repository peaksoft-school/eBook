package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.SortRequest;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.SortService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 26/2/22
 * Saturday 10:17
 * hello world
 */
@SpringBootTest
class SortServiceImplTest {

    private AutoCloseable autoCloseable;

    @Autowired
    private SortService underTest;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookRepository.saveAll(getBooks());
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void firstTest() {
        List<BookResponseDTOSort> sort = underTest.sort(new SortRequest(
                null,
                TypeOfBook.PAPER_BOOK,
                null,
                null
        ));
        sort.forEach(System.out::println);
        assertEquals(getBooks().size(), sort.size());
    }

    private static List<Book> getBooks() {
        return new ArrayList<>(Arrays.asList(
                new Book(
                        null,
                        new HashSet<FileInfo>(
                                Arrays.asList(
                                        new FileInfo()
                                )
                        ),
                        "Think and Get Rich",
                        "I don't know",
                        new Genre(
                                null,
                                "Hobbies",
                                new HashSet<>(),
                                0
                        ),
                        Language.ENGLISH,
                        LocalDate.of(2018, Month.APRIL, 23),
                        new BigDecimal(1000),
                        true,
                        20,
                        TypeOfBook.PAPER_BOOK,
                        "This book about how to get rich, and how to live this life",
                        LocalDate.now(),
                        null,
                        null,
                        new PaperBook(),
                        RequestStatus.ACCEPTED,
                        0,
                        0
                )
        ));
    }


}