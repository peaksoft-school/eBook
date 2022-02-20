package kg.ebooks.eBook.db.repository;

import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

    Optional<Promo> findByPromoName(String promoName);
}
