package kg.ebooks.eBook.db.service.impl;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import kg.ebooks.eBook.db.domain.dto.admin.BookResponseDTOFromAdmin;
import kg.ebooks.eBook.db.domain.dto.book.*;
import kg.ebooks.eBook.db.domain.dto.sort.FilterByTypeOfBookAndGenreId;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.GenreRepository;
import kg.ebooks.eBook.db.service.BookGetService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.ACCEPTED;
import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.INPROGRESS;

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
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Override
    public List<BookResponseDTOFromAdmin> getAllAcceptedBooks(String filterBy) {
        FilterByTypeOfBookAndGenreId filter = null;
        try {
            if (Strings.isNullOrEmpty(filterBy)) {
                filter = null;
            } else {
                filter = gson.fromJson(filterBy, FilterByTypeOfBookAndGenreId.class);
            }
        } catch (Exception e) {
            throw new InvalidRequestException(
                    "Invalid JSON type " + filterBy + " \n " +
                            " correct type : path?filterBy={\"genreId\":1,\"typeOfBook\":\"PAPER_BOOK\"}"
            );
        }

        FilterByTypeOfBookAndGenreId finalFilter = filter;
        return bookRepository.findAll()
                .stream().filter(book -> book.getRequestStatus().equals(ACCEPTED))
                .filter(book -> {
                    if (finalFilter == null) return true;

                    if (finalFilter.getTypeOfBook() == null &&
                            finalFilter.getGenreId() == null) {
                        return true;
                    }

                    boolean equalsA = Objects.equals(book.getGenre().getId(), finalFilter.getGenreId());
                    boolean equalsB = Objects.equals(finalFilter.getTypeOfBook(), book.getTypeOfBook());
                    if (finalFilter.getTypeOfBook() == null) return equalsA;
                    if (finalFilter.getGenreId() == null) return equalsB;

                    return equalsA && equalsB;
                })
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
    public List<GetLikesMaxBooks> getLikesMaxBooks() {
        return bookRepository.bookGetLikesmax()
                .stream()
                .map(book -> modelMapper.map(book, GetLikesMaxBooks.class))
                .limit(3)
                .collect(Collectors.toList());
    }


    @Override
    public List<GetAudioBookDto> getAudioBook() {
        return bookRepository.bookGetAudio()
                .stream()
                .map(book -> modelMapper.map(book, GetAudioBookDto.class))
                .limit(3).collect(Collectors.toList());
    }

    @Override
    public List<GetElectronicBookDTO> getElectronicBook() {
        return bookRepository.bookGetElectronic()
                .stream()
                .map(book -> modelMapper.map(book, GetElectronicBookDTO.class))
                .limit(5).collect(Collectors.toList());
    }

    @Override
    public List<GetBestsellerBookDTO> getBestsellerBook() {
        return bookRepository.bookGetBestseller()
                .stream()
                .map(book -> modelMapper.map(book, GetBestsellerBookDTO.class))
                .limit(3).collect(Collectors.toList());
    }

    @Override
    public GetGenreLastPostBookDTO getGenreLastPost(Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new DoesNotExistsException(
                        String.format("genre with id = %d does not exists in database", genreId)
                ));

        GetGenreLastPostBookDTO postBookDTO = genre.getOriginalBooks()
                .stream()
                .sorted(bookComparator)
                .map(book -> modelMapper.map(book, GetGenreLastPostBookDTO.class))
                .findFirst().orElseThrow(() -> new DoesNotExistsException(
                        "book does not exists in genre with id = " + genreId
                ));
        return postBookDTO;
    }


    private Comparator<Book> bookComparator = (a, b) -> {
        return a.getOriginalStorageDate().isAfter(b.getOriginalStorageDate()) ? 1 :
                a.getOriginalStorageDate().equals(b.getOriginalStorageDate()) ? 0 : -1;
    };


    private Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "book with id = " + bookId + " does not exists"
                ));
    }
}
