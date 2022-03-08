package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.images IN ?1")
    Book findByImage(FileInfo img);

    Book findByBookName(String bookName);

    @Query("select b from Book b order by b.likes desc")
    List<Book> bookGetLikesmax();

    @Query("select b from Book b where b.audioBook is not null")
    List<Book> bookGetAudio();

    @Query("select b from Book b where b.electronicBook is not null")
    List<Book> bookGetElectronic();

    @Query("select b from Book b where b.bestSeller = true ")
    List<Book> bookGetBestseller();
    @Query("SELECT b FROM Book b WHERE b.typeOfBook = ?1")
    List<Book> findByBookType(TypeOfBook typeOfBook);
}
