package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<AuthenticationInfo, Long> {

    @Query("SELECT a FROM AuthenticationInfo a WHERE a.email = :email")
    Optional<AuthenticationInfo> findAuthenticationInfoByEmail(String email);
}
