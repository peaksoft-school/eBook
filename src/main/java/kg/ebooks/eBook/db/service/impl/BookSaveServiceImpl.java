package kg.ebooks.eBook.db.service.impl;

import ch.qos.logback.core.util.COWArrayList;
import com.google.common.collect.Sets;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.dto.genre.GenreDTO;
import kg.ebooks.eBook.db.domain.mapper.BookSaveMapper;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AdminRepository;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.BookSaveService;
import kg.ebooks.eBook.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.Authority.*;
import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 14:35
 * hello world
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookSaveServiceImpl implements BookSaveService {

    private final BookRepository bookRepository;
    private final BookSaveMapper bookSaveMapper;
    private final VendorRepository vendorRepository; // TODO: 5/2/22 VendorService*
    private final AdminRepository adminRepository; // TODO: 5/2/22 AdminService*
    private final FileService fileService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Set<BookResponse> findALLBooks() {
        Set<BookResponse> bookDTOS = bookRepository.findAll().stream()
                .filter(book -> book.getRequestStatus().equals(ACCEPTED))
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toSet());
        log.info("founded {} accepted books", bookDTOS.size());
        return bookDTOS;
    }

    @Override
    @Transactional
    public BookResponse saveBook(AuthenticationInfo authority, TypeOfBook typeOfBook, BookSave<?> book) {
        isBookValid(typeOfBook, book);
        Book response = bookSaveMapper.makeBookFromBookRequest(typeOfBook, book, true);
        System.out.println("test1 : " + response);
        log.info("book with name = [{}], type = {} is storing to database",
                book.getBookName(), typeOfBook);
        Book save;
        if (Objects.equals(authority.getAuthority(), ADMIN)) {
            response.setStorageDate(LocalDate.now());
            response.setRequestStatus(ACCEPTED);
            System.out.println("test2 " + response);
            save = bookRepository.save(response);
            Admin admin = adminRepository.findById(authority.getAuthenticationInfoId())
                    .orElseThrow(() -> new DoesNotExistsException(
                            "admin not found"
                    ));
            admin.setBook(save);
            save.getImages().forEach(FileInfo::makeIsNotFree);
        } else if (Objects.equals(authority.getAuthority(), VENDOR)) {
            response.setRequestStatus(INPROGRESS);
            response.setStorageDate(LocalDate.now());
            System.out.println("test3 " + response);
            save = bookRepository.save(response);
            Vendor vendor = vendorRepository.findById(authority.getAuthenticationInfoId())
                    .orElseThrow(() -> new DoesNotExistsException(
                            "vendor not found"
                    ));
            vendor.setBook(save);
            save.getImages().forEach(FileInfo::makeIsNotFree);
        } else {
            log.error("access forbidden for this user with authority {}", authority);
            throw new AccessForbiddenException(
                    "access forbidden for this user with authority " + authority
            );
        }

        log.info("book with name = [{}], with type = [{}] successfully saved to database", book.getBookName(), typeOfBook);
        return modelMapper.map(save, BookResponse.class);
    }

    private void isBookValid(TypeOfBook typeOfBook, BookSave<?> book) {
        Book byBookName = bookRepository.findByBookName(book.getBookName());
        if (byBookName != null) {
            if (byBookName.getTypeOfBook() == typeOfBook &&
                    byBookName.getLanguage() == book.getLanguage()) {
                log.error("book already [{}] exists in database", book.getBookName());
                throw new AlreadyExistsException(
                        "book already [ " + book.getBookName()+ " ] exists in database"
                );
            }
        }
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long bookId,
                                   TypeOfBook type,
                                   BookSave<?> bookSave) {
        Book book = isValidToUpdate(bookId, type, bookSave);
        Book newBook = bookSaveMapper.makeBookFromBookRequest(type, bookSave, false);
        log.info("there works");

        changeFiles(book, newBook);
        changeType(book, newBook);
        changeBookName(book.getBookName(), newBook.getBookName());
        changeAuthors(book.getAuthor(), newBook.getAuthor());
        changeGenre(book.getGenre(), newBook.getGenre());
        changeDescription(book.getDescription(), newBook.getDescription());
        changeLanguage(book.getLanguage(), newBook.getLanguage());
        changeYearOfIssue(book, newBook);
        changePrice(book.getPrice(), newBook.getPrice());
        changeDiscount(book.getDiscount(), newBook.getDiscount());
        changeBestSeller(book.getBestSeller(), newBook.getBestSeller());
        switch (type) {
            case PAPER_BOOK:
                changeFragment(book.getPaperBook().getFragment(), newBook.getPaperBook().getFragment());
                changePageSize(book.getPaperBook().getPageSize(), newBook.getPaperBook().getPageSize());
                changeQuantityOfBooks(book.getPaperBook().getQuantityOfBooks(), newBook.getPaperBook().getQuantityOfBooks());
                changePublishingHouse(book.getPaperBook().getPublishingHouse(), newBook.getPaperBook().getPublishingHouse());
                break;
            case AUDIO_BOOK:
                changeAudioFragment(book.getAudioBook().getFragment(), newBook.getAudioBook().getFragment());
                changeDuration(book.getAudioBook().getDuration(), newBook.getAudioBook().getDuration());
                changeFile(book.getAudioBook().getAudioBook(), newBook.getAudioBook().getAudioBook());
                break;
            case ELECTRONIC_BOOK:
                changeFragment(book.getElectronicBook().getFragment(), newBook.getFragment());
                changePageSize(book.getElectronicBook().getPageSize(), newBook.getElectronicBook().getPageSize());
                changePublishingHouse(book.getElectronicBook().getPublishingHouse(), newBook.getElectronicBook().getPublishingHouse());
                changeFile(book.getElectronicBook().getElectronicBook(), newBook.getElectronicBook().getElectronicBook());
                break;
        }
        return null;
    }

    private void changeFile(FileInfo file, FileInfo newFile) {
        if (!file.equals(newFile)) {
            fileService.deleteFile(file.getId());
            file = newFile;
        }
    }

    private void changeDuration(LocalTime duration, LocalTime newDuration) {
        if (!duration.equals(newDuration)) {
            duration = newDuration;
        }
    }

    private void changeAudioFragment(FileInfo fragment, FileInfo newFragment) {
        if (!fragment.equals(newFragment)) {
            fileService.deleteFile(fragment.getId());
            fragment = newFragment;
        }
    }

    private void changePublishingHouse(String publishingHouse, String newPublishingHouse) {
        if (!publishingHouse.equals(newPublishingHouse)) {
            publishingHouse = newPublishingHouse;
        }
    }

    private void changeQuantityOfBooks(int quantityOfBooks, int newQuantityOfBooks) {
        if (quantityOfBooks != newQuantityOfBooks) {
            quantityOfBooks = newQuantityOfBooks;
        }
    }

    private void changePageSize(int pageSize, int newPageSize) {
        if (pageSize != newPageSize) {
            pageSize = newPageSize;
        }
    }

    private void changeFragment(String fragment, String newFragment) {
        if (!fragment.equals(newFragment)) {
            fragment = newFragment;
        }
    }

    private void changeBestSeller(Boolean bestSeller, Boolean bestSeller1) {
        if (bestSeller != bestSeller1) {
            bestSeller = bestSeller1;
        }
    }

    private void changeDiscount(int discount, int newDiscount) {
        if (discount != newDiscount) {
            discount = newDiscount;
        }
    }

    private void changePrice(BigDecimal price, BigDecimal price1) {
        if (!price.equals(price1)) {
            price = price1;
        }
    }

    private void changeYearOfIssue(Book book, Book newBook) {
        if (!(book.getYearOfIssue() == newBook.getYearOfIssue())) {
            book.setDateOfIssue(newBook.getDateOfIssue());
        }
    }

    private void changeLanguage(Language language, Language newLanguage) {
        if (!language.equals(newLanguage)) {
            language = newLanguage;
        }
    }

    private void changeDescription(String description, String newDescription) {
        if (!description.equals(newDescription)) {
            description = newDescription;
        }
    }

    private void changeGenre(GenreDTO genre, GenreDTO newGenre) {
        if (!genre.equals(newGenre)) {
            genre = newGenre;
        }
    }

    private void changeAuthors(String author, String author1) {
        if (!author.equals(author1)) {
            author = author1;
        }
    }

    private void changeBookName(String bookName, String newBookName) {
        if (!bookName.equals(newBookName)) {
            bookName = newBookName;
        }
    }

    private void changeType(Book book, Book newBook) {
        if (!book.getTypeOfBook().equals(newBook.getTypeOfBook())) {
            book.setTypeOfBook(newBook.getTypeOfBook());
        }
    }

    private void changeFiles(Book book, Book newBook) {
        log.info("there works 2");

        List<FileInfo> images = book.getImages().stream().collect(Collectors.toList());
        FileInfo[] newImages = (FileInfo[]) newBook.getImages().toArray();

//        for (int i = 0; i < 3; i++) {
//            if (newImages[i] == null) {
//                fileService.deleteFile(images[i].getId());
//                continue;
//            }
//
//            if (!images[i].equals(newImages[i])) {
//                images[i] = newImages[i];
//            }
//        }
    }



    private Book isValidToUpdate(Long bookId, TypeOfBook type, BookSave<?> bookSave) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id = " + bookId + " does not exists in database"
                ));
        if (!type.equals(book.getTypeOfBook())) {
            throw new InvalidRequestException(
                    "books are not same"
            );
        }

        return book;
    }
}
