package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.mapper.BookSaveMapper;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 14:35
 * hello world
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookSaveMapper bookSaveMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Set<BookDTO> findALLBooks() {
        Set<BookDTO> bookDTOS = bookRepository.findAll().stream()
                .filter(book -> book.getRequestStatus().equals(RequestStatus.ACCEPTED))
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toSet());
        log.info("founded {} accepted books", bookDTOS.size());
        return bookDTOS;
    }

    @Override
    public BookDTO saveAudioBook(BookSave<AudioBook> audioBook) {
        Book book = bookSaveMapper.makeBookFromAudioBook(audioBook);
        log.info("book with name [{}] , type [{}] is storing to database",
                audioBook.getBookName(), audioBook.getTypeOfBook());
        bookSaveMapper.setGenreToBook(book, audioBook.getGenreId());
        Book save = bookRepository.save(book);
        log.info("book with name [{}] successfully saved to database", book.getBookName());
        return modelMapper.map(save, BookDTO.class);
    }

    @Override
    public BookDTO saveElectronicBook(BookSave<AudioBook> electronicBook) {
        return null;
    }

    @Override
    public BookDTO savePaperBook(BookSave<AudioBook> paperBook) {
        return null;
    }
}
