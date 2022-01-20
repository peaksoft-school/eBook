package kg.ebooks.eBook.repository;

import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select  u from Client u where u.email = ?1 ")
    Optional<Client> findByEmail(String email);

}
