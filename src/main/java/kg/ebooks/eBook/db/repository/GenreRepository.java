package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.others.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByGenreName(String genreName);
}
