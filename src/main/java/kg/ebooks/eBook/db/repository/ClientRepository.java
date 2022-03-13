package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.email = ?1")
    Optional<Client> findByEmail(String email);
}
