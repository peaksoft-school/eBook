package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.BookInfo;
import kg.ebooks.eBook.db.domain.dto.book.impl.*;
import kg.ebooks.eBook.db.domain.dto.main_card.AudioBookInfoDTO;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfo1;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfoDTO;
import kg.ebooks.eBook.db.domain.dto.main_card.BookInfoForAudio;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.BookService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 21/1/22
 * Friday 12:49
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BookInfo1> getAllBooks() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAudioBook() == null)
                .map(book -> modelMapper.map(book, BookInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookInfoForAudio> getAllAudioBooks() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAudioBook() != null)
                .map(book -> modelMapper.map(book, AudioBookInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookInfo1> getAllBestsellerBooks() {
        return bookRepository.getBestsellerBooks()
                .stream().map(book -> modelMapper.map(book, BookInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookInfo1> getLatestBooks() {
        return bookRepository.findAll().stream()
                .sorted(Comparator.comparing(Book::getStorageDate).reversed())
                .map(book -> modelMapper.map(book, BookInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookInfo1> getAllElectronicBooks() {
        return bookRepository.findElectronicBooks()
                .stream().map(book -> modelMapper.map(book, BookInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookInfo getBookById(Long bookId) {
        Book book = getBook(bookId);

        return switch (book.getTypeOfBook()) {
            case PAPER_BOOK -> modelMapper.map(book, PaperBookDTO.class);
            case AUDIO_BOOK -> modelMapper.map(book, AudioBookDTO.class);
            case ELECTRONIC_BOOK -> modelMapper.map(book, ElectronicBookDTO.class);
        };
    }

    private Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "book with id = " + bookId + " does not exists"
                ));
    }
}