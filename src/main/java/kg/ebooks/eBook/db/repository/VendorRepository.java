package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("SELECT v FROM Vendor v WHERE v.email = ?1")
    Optional<Vendor> findUserByEmail(String email);
}
