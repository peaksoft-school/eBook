package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.mapper.BookSaveMapper;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.Response;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AdminRepository;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.BookSaveService;
import kg.ebooks.eBook.db.service.GenreService;
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
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.Authority.*;
import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;

/**
 * @author Beksultan
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
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;
    private final GenreService genreService;

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
        log.info("book with name = [{}], type = {} is storing to database",
                book.getBookName(), typeOfBook);
        Book save;
        if (Objects.equals(authority.getAuthority(), ADMIN)) {
            response.setStorageDate(LocalDate.now());
            response.setRequestStatus(ACCEPTED);
            save = bookRepository.save(response);
            Admin admin = adminRepository.findByEmail(authority.getEmail())
                    .orElseThrow(() -> new DoesNotExistsException(
                            "admin not found"
                    ));
            admin.setBook(save);
            save.getImages().forEach(FileInfo::makeIsNotFree);
        } else if (Objects.equals(authority.getAuthority(), VENDOR)) {
            response.setRequestStatus(INPROGRESS);
            response.setStorageDate(LocalDate.now());
            save = bookRepository.save(response);
            Vendor vendor = vendorRepository.findUserByEmail(authority.getEmail())
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

    private void works(String works) {
        System.out.println(works);
    }

    private void isBookValid(TypeOfBook typeOfBook, BookSave<?> book) {
        Book byBookName = bookRepository.findByBookName(book.getBookName());
        if (byBookName != null) {
            if (byBookName.getTypeOfBook() == typeOfBook &&
                    byBookName.getLanguage() == book.getLanguage()) {
                log.error("book already [{}] exists in database", book.getBookName());
                throw new AlreadyExistsException(
                        "book already [ " + book.getBookName() + " ] exists in database"
                );
            }
        }
    }

    @Override
//    @Transactional
    public BookResponse updateBook(Long bookId,
                                   TypeOfBook type,
                                   BookSave<?> newBook) {
        Book book = isValidToUpdate(bookId);
        String bookName = book.getBookName();
        log.info("book '{}' able to update", bookName);

        //download images
        Set<FileInfo> newImages = newBook.getImages().stream()
                .map(fileService::findById)
                .collect(Collectors.toSet());

        book.setImages(newImages);

        String newBookName = newBook.getBookName();
        if (isNotNullAndNotEqual(bookName, newBookName) && !newBookName.isEmpty()) {
            book.setBookName(newBookName);
            logInfo("name", bookName, bookName, newBookName);
        }

        String author = book.getAuthor();
        String newAuthor = newBook.getAuthor();
        if (isNotNullAndNotEqual(author, newAuthor) && !newAuthor.isEmpty()) {
            book.setAuthor(newAuthor);
            logInfo("author", bookName, author, newAuthor);
        }

        Genre newGenre = genreRepository.findById(newBook.getGenreId()).orElseThrow(
                () -> new DoesNotExistsException("genre with id = " + newBook.getGenreId() + " does not exists in database")
        );

        Genre byGenreName = genreRepository.findByGenreName(book.getGenre().getGenreName());

        if (isNotNullAndNotEqual(byGenreName, newGenre)) {
            genreService.removeFromGenre(byGenreName.getId(), book);
            book.setGenre(newGenre);
            newGenre.setBook(book);
        }


        String currentDescription = book.getDescription();
        String newDescription = newBook.getDescription();

        if (isNotNullAndNotEqual(currentDescription, newDescription) && newDescription.length() > 0) {
            book.setDescription(newDescription);
            logInfo("description", bookName, currentDescription, newDescription);
        }

        Language currentLanguage = book.getLanguage();
        Language newLanguage = newBook.getLanguage();

        if (isNotNullAndNotEqual(currentLanguage, newLanguage)) {
            book.setLanguage(newLanguage);
            assert currentLanguage != null;
            logInfo("language", bookName, currentLanguage.name(), newLanguage.name());
        }

        int currentYearOfIssue = book.getYearOfIssue();
        int newYearOfIssue = newBook.getYearOfIssue();

        if (isNotNullAndNotEqual(currentYearOfIssue, newYearOfIssue)) {
            book.setYearOfIssue(newYearOfIssue);
            logInfo("dataOfIssue", bookName, String.valueOf(currentYearOfIssue), String.valueOf(newYearOfIssue));
        }

        book.setBestSeller(newBook.getBestSeller());

        BigDecimal currentPrice = book.getPrice();
        BigDecimal newPrice = newBook.getPrice();

        if (isNotNullAndNotEqual(currentPrice, newPrice) && newPrice.intValue() > 0) {
            book.setPrice(newPrice);
            logInfo("price", bookName, currentPrice.toString(), newPrice.toString());
        }

        // FIXME: 12/3/22 discount
        int currentDiscount = book.getDiscount();
        int newDisCount = newBook.getDiscount();

        if (newDisCount != currentDiscount) {
            book.setDiscount(newDisCount);
            logInfo("discount", bookName, String.valueOf(currentDiscount), String.valueOf(newDisCount));
        }

        switch (type) {
            case PAPER_BOOK:
                PaperBookRequest paperBookRequest = (PaperBookRequest) newBook.getBook();

                String currentFragment = book.getPaperBook().getFragment();
                String newFragment = paperBookRequest.getFragment();

                if (isNotNullAndNotEqual(currentFragment, newFragment)) {
                    book.getPaperBook().setFragment(newFragment);
                    logInfo("fragment", bookName, currentFragment, newFragment);
                }

                // FIXME: 12/3/22 quantity of books
                int currentQuantityOfBooks = book.getPaperBook().getQuantityOfBooks();
                int newQuantityOfBooks = paperBookRequest.getQuantityOfBooks();

                if (isNotNullAndNotEqual(currentQuantityOfBooks, newQuantityOfBooks)) {
                    book.getPaperBook().setQuantityOfBooks(newQuantityOfBooks);
                    logInfo("quantityOfBooks", bookName, String.valueOf(currentQuantityOfBooks), String.valueOf(newQuantityOfBooks));
                }

                int currentPageSize = book.getPaperBook().getPageSize();
                int newPageSize = paperBookRequest.getPageSize();

                if (isNotNullAndNotEqual(currentPageSize, newPageSize)) {
                    book.getPaperBook().setPageSize(newPageSize);
                    logInfo("pageSize", bookName, String.valueOf(currentPageSize), String.valueOf(newPageSize));
                }

                String currentPublishingHouse = book.getPaperBook().getPublishingHouse();
                String newPublishingHouse = paperBookRequest.getPublishingHouse();

                if (isNotNullAndNotEqual(currentPublishingHouse, newPublishingHouse)) {
                    book.getPaperBook().setPublishingHouse(newPublishingHouse);
                    logInfo("publishingHouse", bookName, currentPublishingHouse, newPublishingHouse);
                }
                break;
            case ELECTRONIC_BOOK:
                ElectronicBookRequest electronicBookRequest = (ElectronicBookRequest) newBook.getBook();

                String currentEFragment = book.getPaperBook().getFragment();
                String newEFragment = electronicBookRequest.getFragment();

                if (isNotNullAndNotEqual(currentEFragment, newEFragment)) {
                    book.getPaperBook().setFragment(newEFragment);
                    logInfo("fragment", bookName, currentEFragment, newEFragment);
                }

                // FIXME: 12/3/22 quantity of books
                int currentEPageSize = book.getPaperBook().getPageSize();
                int newEPageSize = electronicBookRequest.getPageSize();

                if (isNotNullAndNotEqual(currentEPageSize, newEPageSize)) {
                    book.getPaperBook().setPageSize(newEPageSize);
                    logInfo("pageSize", bookName, String.valueOf(currentEPageSize), String.valueOf(newEPageSize));
                }

                String currentEPublishingHouse = book.getPaperBook().getPublishingHouse();
                String newEPublishingHouse = electronicBookRequest.getPublishingHouse();

                if (isNotNullAndNotEqual(currentEPublishingHouse, newEPublishingHouse)) {
                    book.getPaperBook().setPublishingHouse(newEPublishingHouse);
                    logInfo("publishingHouse", bookName, currentEPublishingHouse, newEPublishingHouse);
                }

                FileInfo currentElectronicBook = book.getElectronicBook().getElectronicBook();
                FileInfo newElectronicBook = fileService.findById(electronicBookRequest.getElectronicBookId());

                if (isNotNullAndNotEqual(currentElectronicBook, newElectronicBook)) {
                    fileService.deleteFile(currentElectronicBook.getId());
                    book.getElectronicBook().setElectronicBook(newElectronicBook);
                    logInfo("electronicBook", bookName, currentElectronicBook.toString(), newElectronicBook.toString());
                }
                break;
            case AUDIO_BOOK:

                AudioBookRequest audioBookRequest = (AudioBookRequest) newBook.getBook();

                FileInfo currentAFragment = book.getAudioBook().getFragment();
                FileInfo newAFragment = fileService.findById(audioBookRequest.getFragmentId());

                if (isNotNullAndNotEqual(currentAFragment, newAFragment)) {
                    fileService.deleteFile(currentAFragment.getId());
                    book.getAudioBook().setFragment(newAFragment);
                    logInfo("fragmentId", bookName, currentAFragment.toString(), newAFragment.toString());
                }


                LocalTime currentDuration = book.getAudioBook().getDuration();
                LocalTime newDuration = audioBookRequest.getDuration().makeLocalTime();
                if (isNotNullAndNotEqual(currentDuration, newDuration)) {
                    book.getAudioBook().setDuration(newDuration);
                    logInfo("duration", bookName, currentDuration.toString(), newDuration.toString());
                }

                FileInfo currentAudioBook = book.getAudioBook().getAudioBook();
                FileInfo newAudioBook = fileService.findById(audioBookRequest.getAudioBookId());

                if (isNotNullAndNotEqual(currentAudioBook, newAudioBook)) {
                    fileService.deleteFile(currentAudioBook.getId());
                    book.getAudioBook().setAudioBook(newAudioBook);
                    logInfo("audioBook", bookName, currentAudioBook.toString(), newAudioBook.toString());
                }

                break;
        }
        System.out.println("this so crazy");
        Book save = bookRepository.save(book);
        return modelMapper.map(save, BookResponse.class);
    }

    private <T> boolean isNotNullAndNotEqual(T current, T news) {
        return news != null && !Objects.equals(current, news);
    }

    private void logInfo(String what, String which, String current, String news) {
        log.info("{} of book '{}' updated from '{}' to '{}'", what, which, current, news);
    }


    private Book isValidToUpdate(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "book with id = " + bookId + " does not exists in database"
                ));
    }

    @Override
//    @Transactional
    public Response deleteBook(String email, Authority authority, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        "book with id = " + bookId + " does not exists"
                ));

        removeFromVendor(book);

        switch (authority) {
            case ADMIN:
                log.info("admin");
                try {


                    Long id = book.getOriginalGenre().getId();

                    book.removeGenre();
                    bookRepository.save(book);

                    genreService.removeFromGenre(id, book);
                    bookRepository.deleteById(bookId);
                    return Response.SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.FAIL;
                }

            case VENDOR:
                log.info("vendor");
                Vendor vendor = vendorRepository.findUserByEmail(email)
                        .orElseThrow(() -> new NotFoundException(
                                "Vendor with email = " + email + " does not exists!"
                        ));

                if (!vendor.getBooksToSale().contains(book)) {
                    throw new InvalidRequestException(
                            "book is not yours"
                    );
                }

                Long id = book.getOriginalGenre().getId();

                book.removeGenre();
                bookRepository.save(book);

                genreService.removeFromGenre(id, book);

                try {
                    bookRepository.deleteById(bookId);
                    return Response.SUCCESS;
                } catch (Exception e) {
                    return Response.FAIL;
                }
        }
        return Response.SUCCESS;
    }

    private void removeFromVendor(Book book) {
        Vendor vendor = vendorRepository.findAll()
                .stream()
                .filter(v -> v.getBooksToSale().contains(book))
                .findAny()
                .orElseThrow(() -> new NotFoundException(
                        "vendor with book = " + book.getBookName() + " does not exists"
                ));
        log.info("removing book: {}", book.getBookName());
        vendor.getBooksToSale().remove(book);
        vendorRepository.save(vendor);
    }
}
