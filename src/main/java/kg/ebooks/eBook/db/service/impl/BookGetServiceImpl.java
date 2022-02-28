package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.admin.BookResponseDTOFromAdmin;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResponse;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.BookGetService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:32
 * hello world
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookGetServiceImpl implements BookGetService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BookResponseDTOFromAdmin> getAllAcceptedBooks() {
        return bookRepository.findAll()
                .stream().filter(book -> book.getRequestStatus().equals(ACCEPTED))
                .map(book -> modelMapper.map(book, BookResponseDTOFromAdmin.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTOFromAdmin> getAllBooksStorageRequests() {
        return bookRepository.findAll()
                .stream().filter(book -> book.getRequestStatus().equals(INPROGRESS))
                .map(book -> modelMapper.map(book, BookResponseDTOFromAdmin.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookInfo getBookById(Long bookId) {
        Book book = getBook(bookId);

        switch (book.getTypeOfBook()) {
            case PAPER_BOOK:
                return modelMapper.map(book, PaperBookDTO.class);
            case AUDIO_BOOK:
                return modelMapper.map(book, AudioBookDTO.class);
            case ELECTRONIC_BOOK:
                return modelMapper.map(book, ElectronicBookDTO.class);
            default:
                return null;
        }
    }

    @Override
    public List<BookMainPage> getThreeBooks() {
        return bookRepository.bookGetLikesmax()
                .stream().map(this::bookMainPage).limit(3)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookMainPage bookMainPage(Book book) {
        BookMainPage bookMainPage = new BookMainPage();
        bookMainPage.setBookId(book.getBookId());
        bookMainPage.setBookName(book.getBookName());
        bookMainPage.setAuthor(book.getAuthor());
        bookMainPage.setPrice(book.getPrice());
        bookMainPage.setImage(book.getImage());
        return bookMainPage;
    }

    @Override
    public List<GetAudioBookDto> getAudioBook() {
        return bookRepository.findAll()
                .stream().map(this::getAudiobooks)
                .limit(5).collect(Collectors.toList());
    }

    @Transactional
    public GetAudioBookDto getAudiobooks(Book book) {
        GetAudioBookDto getAudioBookDto = new GetAudioBookDto();
        getAudioBookDto.setBookId(book.getBookId());
        getAudioBookDto.setBookName(book.getBookName());
        getAudioBookDto.setAuthor(book.getAuthor());
        getAudioBookDto.setPrice(book.getPrice());
        getAudioBookDto.setImage(book.getImage());
        return getAudioBookDto;
    }


    private Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "book with id = " + bookId + " does not exists"
                ));
    }
}
