package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByGenreName(String genreName);

    Genre findByGenreName(String genreName);

    @Query("select b from Book b where b.genre.id = :genreId order by b.storageDate desc")
    List<Book> findLastBook(@Param("genreId") Long id);

}
