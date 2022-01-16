package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
