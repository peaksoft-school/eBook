package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.book.BookSave;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 14:34
 * hello world
 */
public interface BookService {

    Set<BookDTO> findALLBooks();

    BookDTO saveAudioBook(BookSave<AudioBook> audioBook);

    BookDTO saveElectronicBook(BookSave<AudioBook> electronicBook);

    BookDTO savePaperBook(BookSave<AudioBook> paperBook);

}
