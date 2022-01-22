package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.dto.main_card.BookInfo1;
import kg.ebooks.eBook.db.domain.model.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("SELECT b FROM Book b WHERE b.bestSeller = true")
    List<Book> getBestsellerBooks();

    @Query("SELECT e FROM Book e WHERE e.electronicBook IS NOT NULL")
    List<BookInfo1> findElectronicBooks();
}
