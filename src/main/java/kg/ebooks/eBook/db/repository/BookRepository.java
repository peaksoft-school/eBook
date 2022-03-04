package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.images IN ?1")
    Book findByImage(FileInfo img);

    Book findByBookName(String bookName);

    @Query("SELECT b FROM Book b WHERE b.typeOfBook = ?1")
    List<Book> findByBookType(TypeOfBook typeOfBook);
}
