package kg.ebooks.eBook.config;

import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Genre;
import kg.ebooks.eBook.db.domain.model.others.Image;
import kg.ebooks.eBook.db.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(
            AddressRepository addressRepository,
            AdminRepository adminRepository,
            AuthenticationInfoRepository authenticationInfoRepository,
            BasketRepository basketRepository,
            BookRepository bookRepository,
            ClientRepository clientRepository,
            GenreRepository genreRepository,
            PromoRepository promoRepository,
            SelectedBooksRepository selectedBooksRepository,
            VendorRepository vendorRepository) {
        return args -> {

            //images
            Image image1 = new Image();
            image1.setImageName("educated image");
            image1.setContent(new byte[10]); // test

            Image image2 = new Image();
            image1.setImageName("educated image 2");
            image1.setContent(new byte[11]); // test

            //genres
            Genre genre1 = new Genre();
            genre1.setGenreName("Romantic");
            genre1.setQuantityOfBooks(12); //test

            Genre genre2 = new Genre();
            genre1.setGenreName("Biography");
            genre1.setQuantityOfBooks(50); //test

            // audioBooks
            AudioBook audioBook = new AudioBook();
            // TODO: 10/1/22 set value to fields when finished audiobook

            // paper books
            PaperBook paperBook = new PaperBook();
            // TODO: 10/1/22 set value to fields when finished paper book

            //electronic books
            ElectronicBook electronicBook = new ElectronicBook();
            // TODO: 10/1/22 set value to fields when finished electronic book

            // books
            Book educated = new Book();
            educated.setImages(Arrays.asList(image1, image2));
            educated.setBookName("Educated");
            educated.setAuthorFullName("Tara Westover");
            educated.setGenre(genre2);
            educated.setPublishingHouseName("Amazon"); //test
            educated.setLanguage(Language.ENGLISH);
            educated.setDateOfIssue(LocalDate.of(2018, Month.FEBRUARY, 18));
            educated.setPageSize(420);
            educated.setPrise(new BigDecimal("24.3$"));
            educated.setBestSeller(true);
            educated.setDiscount(new BigDecimal("3$"));
            educated.setDescription("This book is the best book i had ever read!");
            educated.setTypeOfBook(TypeOfBook.ELECTRONIC_BOOK);
            educated.setElectronicBook(electronicBook);

            Book first_teacher = new Book();
            educated.setImages(Arrays.asList(image1, image2));
            educated.setBookName("First Teacher");
            educated.setAuthorFullName("CHYNGYZ AITMATOV");
            educated.setGenre(genre1);
            educated.setPublishingHouseName("Amazon"); //test
            educated.setLanguage(Language.ENGLISH);
            educated.setDateOfIssue(LocalDate.of(2018, Month.JULY, 18));
            educated.setPageSize(420);
            educated.setPrise(new BigDecimal("24.3$"));
            educated.setBestSeller(true);
            educated.setDiscount(new BigDecimal("3$"));
            educated.setDescription("This book is the best book i had ever read!");
            educated.setTypeOfBook(TypeOfBook.PAPER_BOOK);
            educated.setElectronicBook(electronicBook);
        };
    }
}

