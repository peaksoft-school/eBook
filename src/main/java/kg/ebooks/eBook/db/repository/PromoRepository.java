package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.others.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
