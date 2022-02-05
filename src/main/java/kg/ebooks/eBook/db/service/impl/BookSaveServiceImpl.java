package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.mapper.BookSaveMapper;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AdminRepository;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.BookSaveService;
import kg.ebooks.eBook.exceptions.AccessForbiddenException;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
        Book response = bookSaveMapper.makeBookFromBookRequest(typeOfBook, book);
        log.info("book with name = [{}], type = {} is storing to database",
                book.getBookName(), typeOfBook);
        Book save;
        if (Objects.equals(authority.getAuthority(), ADMIN)) {
            response.setRequestStatus(ACCEPTED);
            save = bookRepository.save(response);
            Admin admin = adminRepository.findById(authority.getAuthenticationInfoId())
                    .orElseThrow(() -> new DoesNotExistsException(
                            "admin not found"
                    ));
            save.setStorageDate(LocalDate.now());
            admin.setBook(save);
        } else if (Objects.equals(authority.getAuthority(), VENDOR)) {
            response.setRequestStatus(INPROGRESS);
            save = bookRepository.save(response);
            Vendor vendor = vendorRepository.findById(authority.getAuthenticationInfoId())
                    .orElseThrow(() -> new DoesNotExistsException(
                            "vendor not found"
                    ));
            save.setStorageDate(LocalDate.now());
            vendor.setBook(save);
        } else {
            log.error("access forbidden for this user with authority {}", authority);
            throw new AccessForbiddenException(
                    "access forbidden for this user with authority " + authority
            );
        }

        log.info("book with name = [{}], with type = [{}] successfully saved to database", book.getBookName(), typeOfBook);
        return modelMapper.map(save, BookResponse.class);
    }

    private boolean isBookValid(TypeOfBook typeOfBook, BookSave<?> book) {
        Book byBookName = bookRepository.findByBookName(book.getBookName());
        if (byBookName != null) {
            if (byBookName.getTypeOfBook() == typeOfBook &&
                    byBookName.getLanguage() == book.getLanguage()) {
                log.error("book already [{}] exists in database", book.getBookName());
                throw new AlreadyExistsException(
                        "book already [ " + book.getBookName()+ " ] exists"
                );
            }
        }
        return true;
    }
}
