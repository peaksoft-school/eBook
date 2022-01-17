package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findUserByEmail(String email);
}
