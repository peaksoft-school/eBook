package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.*;

import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 14:34
 * hello world
 */
public interface BookSaveService {

    Set<BookDTO> findALLBooks();

    BookDTO saveAudioBook(BookSave<AudioDTO> audioBook);

    BookDTO saveElectronicBook(BookSave<ElectronicDTO> electronicBook);

    BookDTO savePaperBook(BookSave<PaperBookSaveDTO> paperBook);

}
