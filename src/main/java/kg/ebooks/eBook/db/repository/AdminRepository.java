package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
