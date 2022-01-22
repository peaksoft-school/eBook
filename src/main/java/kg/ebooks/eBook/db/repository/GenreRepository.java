package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.others.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByGenreName(String genreName);

    boolean existsByGenreName(String genreName);
}
