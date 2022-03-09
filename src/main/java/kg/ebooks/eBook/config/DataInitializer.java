package kg.ebooks.eBook.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.repository.FileRepository;
import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.*;
import kg.ebooks.eBook.db.domain.model.others.*;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;

@Component
public class DataInitializer {


//    @Bean
//    @Transactional
    CommandLineRunner commandLineRunner(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            VendorRepository vendorRepository,
            PasswordEncoder passwordEncoder,
            GenreRepository genreRepository,
            BookRepository bookRepository,
            FileRepository fileRepository,
            PromoRepository promoRepository) {
        return args -> {
            //client
            Client client = new Client();
            client.setName("client");
            client.setEmail("client@gmail.com");
            client.setAuthenticationInfo(
                    new AuthenticationInfo(
                            null,
                            Authority.CLIENT,
                            passwordEncoder.encode("client"),
                            "client@gmail.com",
                            true,
                            true,
                            true,
                            true
                    )
            );

            //vendor
            Vendor vendor = new Vendor();
            vendor.setFirstName("vendor");
            vendor.setLastName("vendorovic");
            vendor.setEmail("vendor@gmail.com");
            vendor.setPhoneNumber("+996552123456");
            vendor.setNameOfBranch("Book House");
            vendor.setAddress(
                    new Address(
                            null,
                            Country.KYRGYZSTAN,
                            "Bishkek",
                            "Grazhdanski 119",
                            "723843"
                    )
            );
            vendor.setAuthenticationInfo(
                    new AuthenticationInfo(
                            null,
                            Authority.VENDOR,
                            passwordEncoder.encode("vendor"),
                            "vendor@gmail.com",
                            true,
                            true,
                            true,
                            true
                    )
            );

            //admin

            Admin admin = new Admin();
            admin.setFirstName("admin");
            admin.setLastName("admin uulu");
            admin.setEmail("admin@gmail.com");
            admin.setAuthenticationInfo(
                    new AuthenticationInfo(
                            null,
                            Authority.ADMIN,
                            passwordEncoder.encode("admin"),
                            "admin@gmail.com",
                            true,
                            true,
                            true,
                            true
                    )
            );

            Genre genre = new Genre("Adventure");
            Genre genre2 = new Genre("Romance");
            Genre genre3 = new Genre("Children Fiction");

            genreRepository.save(genre);
            genreRepository.save(genre2);
            genreRepository.save(genre3);

            FileInfo audioFragment = new FileInfo();
            audioFragment.setFileName("503be825-1f37-4c49-9966-61395de63a51/Erlan_Andashev_Uyalasyin_go_(super.kg).mp3");
            audioFragment.setFolderName(FolderName.AUDIO_FRAGMENTS_FILES);
            audioFragment.setFree(true);
            fileRepository.save(audioFragment);

            FileInfo audioBookFile = new FileInfo();
            audioBookFile.setFileName("9eca87f1-adca-4e7e-9e37-106d88cc15eb/Erlan_Andashev_Uyalasyin_go_(super.kg).mp3");
            audioBookFile.setFolderName(FolderName.AUDIO_FILES);
            audioBookFile.setFree(true);
            fileRepository.save(audioBookFile);

            FileInfo electronicBookFile = new FileInfo();
            electronicBookFile.setFolderName(FolderName.PDF_FILES);
            electronicBookFile.setFileName("ba10dfad-82cb-4e04-9240-67b8e167d8a7/Занятие_18___Домашнее_задание.pdf");
            electronicBookFile.setFree(true);
            fileRepository.save(electronicBookFile);

            //audio book
            Book audioBook = new Book();
            HashSet<FileInfo> images2 = Sets.newHashSet(
                    makeAnNewImage("85f9419c-60e5-41bb-8e9c-2a51b673971f/davincicode.png"),
                    makeAnNewImage("9cbdfa57-bccb-4307-9608-8d3c156ee569/davincicode.png"),
                    makeAnNewImage("2c8593be-b6d2-4d71-9668-86e888a105c1/davincicode.png")
            );
            fileRepository.saveAll(images2);

            audioBook.setImages(images2);
            audioBook.setBookName("The Da Vinci Code");
            audioBook.setAuthor("Dan Brown");
            audioBook.setGenre(genre);
            audioBook.setDescription("this is audio book description");
            audioBook.setLanguage(Language.ENGLISH);
            audioBook.setDateOfIssue(LocalDate.of(2017, Month.APRIL, 23));
            audioBook.setBestSeller(false);
            audioBook.setPrice(new BigDecimal(1234));
            audioBook.setDiscount(30);
            audioBook.setAudioBook(
                    new AudioBook(
                            null,
                            audioFragment,
                            LocalTime.of(4, 23, 54),
                            audioBookFile
                    )
            );
            audioBook.setTypeOfBook(TypeOfBook.AUDIO_BOOK);

            //electronic book
            Book electronicBook = new Book();
            HashSet<FileInfo> images1 = Sets.newHashSet(
                    makeAnNewImage("e8aae7e9-4f6e-4895-b540-8fb5cb712594/electronicBooks.png"),
                    makeAnNewImage("f579de9b-c959-43fb-8fc4-d8348c4c7589/electronicBooks.png"),
                    makeAnNewImage("a4dad1ab-1aed-4329-92eb-d96cdd9a94bc/electronicBooks.png")
            );
            fileRepository.saveAll(images1);

            electronicBook.setImages(
                    images1
            );
            electronicBook.setBookName("GreenLights");
            electronicBook.setAuthor("Matthew McConaughey");
            electronicBook.setGenre(genre2);
            electronicBook.setDescription("this is electronic book description");
            electronicBook.setLanguage(Language.RUSSIAN);
            electronicBook.setDateOfIssue(LocalDate.of(2020, Month.OCTOBER, 20));
            electronicBook.setBestSeller(true);
            electronicBook.setPrice(new BigDecimal(2350));
            electronicBook.setDiscount(15);
            electronicBook.setElectronicBook(
                    new ElectronicBook(
                            null,
                            "this is electronic book fragment",
                            304,
                            "Crown Publishing Group",
                            electronicBookFile
                    )
            );
            electronicBook.setTypeOfBook(TypeOfBook.ELECTRONIC_BOOK);

            //paper book
            Book paperBook = new Book();
            HashSet<FileInfo> images3 = Sets.newHashSet(
                    makeAnNewImage("dd90f5fd-1497-424b-973a-a6ee7369519b/atomic-habits-dots.png"),
                    makeAnNewImage("dd346267-c5bc-4abb-8047-cca2742bcd08/atomic-habits-dots.png"),
                    makeAnNewImage("fb307941-ed1d-45e3-a4e9-247b1d1c0195/atomic-habits-dots.png")
            );
            fileRepository.saveAll(images3);
            paperBook.setImages(
                    images3
            );
            paperBook.setBookName("Atomic Habits");
            paperBook.setAuthor("James Clear");
            paperBook.setGenre(genre3);
            paperBook.setDescription("this is paper book description");
            paperBook.setLanguage(Language.KYRGYZ);
            paperBook.setDateOfIssue(LocalDate.of(2018, Month.NOVEMBER, 3));
            paperBook.setBestSeller(true);
            paperBook.setPrice(new BigDecimal(3400));
            paperBook.setDiscount(10);
            paperBook.setPaperBook(
                    new PaperBook(
                            null,
                            "this is paper book fragment",
                            30,
                            240,
                            "Amazon official publishing house"
                    )
            );
            paperBook.setTypeOfBook(TypeOfBook.PAPER_BOOK);

            Promo promoCode = new Promo(
                    null,
                    "InternationWomensDay",
                    LocalDate.of(2022, Month.APRIL, 20),
                    LocalDate.of(2022, Month.SEPTEMBER, 20),
                    (byte) 40,
                    vendor
            );

            audioBook.setRequestStatus(RequestStatus.ACCEPTED);
            paperBook.setRequestStatus(RequestStatus.ACCEPTED);
            electronicBook.setRequestStatus(RequestStatus.ACCEPTED);

            audioBook.setStorageDate(LocalDate.now());
            paperBook.setStorageDate(LocalDate.now());
            electronicBook.setStorageDate(LocalDate.now());

            client.setDateOfRegistration(LocalDate.now());
            vendor.setDateOfRegistration(LocalDate.now());

            promoRepository.save(promoCode);

            vendor.setPromoCode(promoCode);

            bookRepository.save(paperBook);
            bookRepository.save(audioBook);
            bookRepository.save(electronicBook);

            admin.setBooks(Lists.newArrayList(audioBook));
            vendor.setBooksToSale(Lists.newArrayList(paperBook, electronicBook));

            client.getBasket().setBooks(Lists.newArrayList(audioBook, paperBook));
            client.getSelectedBooks().setBooks(Lists.newArrayList(electronicBook));

            clientRepository.save(client);
            adminRepository.save(admin);
            vendorRepository.save(vendor);

            genreRepository.save(genre);
            genreRepository.save(genre2);
            genreRepository.save(genre3);

            bookRepository.save(paperBook);
            bookRepository.save(audioBook);
            bookRepository.save(electronicBook);

        };
    }

    private FileInfo makeAnNewImage(String fileName) {
        return new FileInfo(
                null,
                FolderName.IMAGES,
                fileName,
                true
        );
    }
}

