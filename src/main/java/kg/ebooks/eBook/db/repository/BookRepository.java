package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.images IN ?1")
    Book findByImage(FileInfo img);

    Book findByBookName(String bookName);


    @Query("select n from Book n  where" +
            " n.bookName LIKE CONCAT( :search, '%') or " +
            "n.genre.genreName LIKE CONCAT(:search, '%') OR " +
            "n.author LIKE CONCAT(:search, '%')")
    List<Book> listsearch1(@Param("search") String search);

    @Query("select b.bookId, b.bookName as search from Book b where b.bookName like concat( :search, '%')")
    List<Book> findBookNameBySearch(@Param("search") String search);

    @Query("select b.bookId, b.genre.genreName as search from Book b where b.genre.genreName like concat( :search, '%')")
    List<Book> findGenreNameBySearch(@Param("search") String search);

    @Query("select b.bookId, b.author as search from Book b where b.author like concat( :search, '%')")
    List<Book> findAuthorBySearch(@Param("search") String search);


}
