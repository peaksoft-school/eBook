package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("SELECT v FROM Vendor v WHERE v.email = ?1")
    Optional<Vendor> findUserByEmail(String email);
}
