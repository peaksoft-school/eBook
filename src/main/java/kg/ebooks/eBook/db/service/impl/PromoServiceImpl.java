package kg.ebooks.eBook.db.service.impl;

import com.google.gson.Gson;
import kg.ebooks.eBook.db.domain.dto.Response;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.repository.PromoRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.PromoService;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by Beksultan Mamatkadyr uulu
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;
    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;
    private final VendorService vendorService;
    private final Gson gson;

    @Override
    @Transactional
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
        save.setPromoCreator(vendor);
        log.info("promo [{}] successfully created", promo.getPromoName());

        return "Promo successfully created";
    }

    @Override
    public Set<BookResponse> findPromo(String promo) {
        Promo promocode = promoRepository.findByPromoName(promo)
                .orElseThrow(() -> new PromoNotFoundException(
                        "promo with promo-name = " + promo + " does not exists"
                ));

        return promocode.getPromoCreator()
                .getBooksToSale()
                .stream().map(promocode::addPromoToBook)
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public String activatePromo(String email, String promoName) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        String.format("client with email = %s does not exists", email)
                ));


        Promo promo = promoRepository.findByPromoName(promoName)
                .orElseThrow(() -> new NotFoundException(
                        String.format("promo with promo name = %s not found", promoName)
                ));

        if (client.getBasket().getPromocode() != null) {
            throw new InvalidPromoException(
                    "promo code already exists"
            );
        }

        if (!promo.isValid()) {
            throw new InvalidPromoException(
                    "promo date expired or not starting yet"
            );
        }

        List<Book> books = client.getBasket().getBooks();
        if (!isContains(books, promo.getPromoCreator().getBooksToSale())) {
            throw new InvalidPromoException(
                    "you don't have any books that qualify for this promocode"
            );
        }

        client.getBasket().setPromo(promo);

        log.info("{} successfully added promocode = {} to his basket", client.getName(), promo.getPromoName());

        return gson.toJson(new Response("SUCCESS"));
    }

    private boolean isContains(List<Book> clientBooks, Set<Book> vendorBooks) {
        for (Book clientBook : clientBooks) {
            System.out.println(clientBook.getBookName());
            if (vendorBooks.contains(clientBook)) {
                return true;
            }
        }
        return false;
    }
}
