package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.PromoRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.PromoService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.InvalidDateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 02:35
 * hello world
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;
    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public String createPromo(String email, PromoCreate promo) {
        // check is valid dates
        LocalDate startingDay = promo.getStartingDay();
        LocalDate finishingDay = promo.getFinishingDay();
        if (startingDay.isAfter(finishingDay)) {
            throw new InvalidDateException(
                    "your finishing day before than starting day"
            );
        } else if (startingDay.plusDays(1L).isAfter(finishingDay)) {
            throw new InvalidDateException(
                    "you have to define min 1 day to promo code"
            );
        }

        // check is does promo exists in database
        String promoName = promo.getPromoName();
        if (promoRepository.findByPromoName(promoName).isPresent()) {
            log.error("promo with promo-name = '{}' has already exists in database", promo.getPromoName());
            throw new AlreadyExistsException(
                    "promo with promo-name = '" + promoName + "' has already exists\n" +
                            " choose another name"
            );
        }

        // cast promoCreate to Promo.class
        Promo promo1 = modelMapper.map(promo, Promo.class);

        // or else create new Promo
        Promo save = promoRepository.save(promo1);

        //find client with email
        Vendor vendor = vendorRepository.findUserByEmail(email)
                .orElseThrow(() -> new AlreadyExistsException(
                        "vendor with email = " + email + " does exists"
                ));

        // set Promo to all his books
        vendor.setPromoCode(save);
        log.info("promo [{}] successfully created", promo.getPromoName());

        return "Promo successfully created";
    }
}
