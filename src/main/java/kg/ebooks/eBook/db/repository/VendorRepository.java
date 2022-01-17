package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findUserByEmail(String email);

}
