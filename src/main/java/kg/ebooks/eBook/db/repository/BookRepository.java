package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.sort.Price;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Query("select b from Book b where b.language in :languages or b.language is null and" +
            " b.genre.id in :genres or b.genre is null and" +
            " b.price > :originPrice and" +
            " b.price < :boundPrice and" +
            " b.typeOfBook = :typeOfBook")
    Set<Book> filterBy(@Param(value = "languages") Set<Integer> languages,
                       @Param(value = "genres") Set<Long> genres,
                       @Param(value = "originPrice") int originPrice,
                       @Param(value = "boundPrice") int boundPrice,
                       @Param(value = "typeOfBook") TypeOfBook typeOfBook);

    @Query(" select b from Book b " +
            " where b.genre.id in :genres and" +
            " b.language in :languages ")
    Set<Book> findByGenreId(@Param("genres") Set<Long> genres,
                            @Param("languages") Set<Language> languages);

    Set<Book> findByAuthor(String authorName);

    @Query(" select b from Book b where b.typeOfBook in :types")
    Set<Book> findElectronicAndPaperBooks(@Param("types") List<TypeOfBook> typesOfBooks);

    @Query("select b from Book b where b.typeOfBook = :bookType and " +
            " b.requestStatus = :requestStatus and " +
            " b.bestSeller = true " +
            " order by b.likes desc ")
    List<Book> findTheMostPopularBooks(@Param("bookType") TypeOfBook bookType,
                                                         @Param("requestStatus") RequestStatus requestStatus);

    @Query("select b from Book b where b.requestStatus = :accepted order by b.storageDate desc ")
    List<Book> findLastPublications(@Param("accepted") RequestStatus accepted);

    @Query("select b from Book b where b.requestStatus = :accepted and b.bestSeller = true order by b.likes desc ")
    List<Book> findBestSellerBooks(@Param("accepted") RequestStatus accepted);

    @Query("select b from Book b where b.requestStatus = :accepted and " +
            " b.bestSeller = true order by b.likes desc ")
    List<Book> findPopularBooks(@Param("accepted") RequestStatus accepted);
}
