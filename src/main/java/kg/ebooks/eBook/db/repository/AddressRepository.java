package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.others.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
