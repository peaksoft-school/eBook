package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
