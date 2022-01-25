package kg.ebooks.eBook.config;

import kg.ebooks.eBook.db.domain.model.books.AudioBook;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.books.ElectronicBook;
import kg.ebooks.eBook.db.domain.model.books.PaperBook;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.enums.Country;
import kg.ebooks.eBook.db.domain.model.enums.Language;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.*;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

//    @Bean
    CommandLineRunner commandLineRunner(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            VendorRepository vendorRepository) {
        return args -> {

            //images
            Image image1 = new Image();
            image1.setImageName("educated image");
            image1.setContent(new byte[10]); // test

            Image image2 = new Image();
            image2.setImageName("educated image 2");
            image2.setContent(new byte[6]); // test

            Image image3 = new Image();
            image3.setImageName("educated image 3");
            image3.setContent(new byte[12]); // test

            Image image4 = new Image();
            image4.setImageName("educated image 4");
            image4.setContent(new byte[5]); // test

            Image image5 = new Image();
            image5.setImageName("educated image 5");
            image5.setContent(new byte[7]); // test

            //genres
            Genre genre1 = new Genre();
            genre1.setGenreName("Romantic");
            genre1.setQuantityOfBooks(12); //test

            Genre genre3 = new Genre();
            genre3.setGenreName("loves");
            genre3.setQuantityOfBooks(12); //test

            Genre genre4 = new Genre();
            genre4.setGenreName("Romantic");
            genre4.setQuantityOfBooks(12); //test

            Genre genre5 = new Genre();
            genre5.setGenreName("loves");
            genre5.setQuantityOfBooks(12); //test

            Genre genre2 = new Genre();
            genre2.setGenreName("Biography");
            genre2.setQuantityOfBooks(50); //test

            // audioBooks
            AudioBook audioBook = new AudioBook();
            // TODO: 10/1/22 set value to fields when finished audiobook

            // paper books
            PaperBook paperBook = new PaperBook();
            // TODO: 10/1/22 set value to fields when finished paper book

            //electronic books

            ElectronicBook electronicBook = new ElectronicBook();
            ElectronicBook electronicBook2 = new ElectronicBook();
            // TODO: 10/1/22 set value to fields when finished electronic book

            // book-educated

            Book educated = new Book();
            educated.setImages(Arrays.asList(image1));
            educated.setBookName("Educated");
            educated.setAuthorFullName("Tara Westover");
            educated.setGenre(genre2);
            educated.setPublishingHouseName("Amazon"); //test
            educated.setLanguage(Language.ENGLISH);
            educated.setDateOfIssue(LocalDate.of(2018, Month.FEBRUARY, 18));
            educated.setPageSize(420);
            educated.setPrise(new BigDecimal("24.3"));
            educated.setBestSeller(true);
            educated.setDiscount(new BigDecimal("3"));
            educated.setDescription("This book is the best book i had ever read!");
            educated.setTypeOfBook(TypeOfBook.AUDIO_BOOK);
            educated.setAudioBook(audioBook);

            //book-first_teacher

            Book first_teacher = new Book();
            first_teacher.setImages(Arrays.asList(image4));
            first_teacher.setBookName("First Teacher");
            first_teacher.setAuthorFullName("CHYNGYZ AITMATOV");
            first_teacher.setGenre(genre4);
            first_teacher.setPublishingHouseName("Amazon"); //test
            first_teacher.setLanguage(Language.ENGLISH);
            first_teacher.setDateOfIssue(LocalDate.of(2018, Month.JULY, 18));
            first_teacher.setPageSize(420);
            first_teacher.setPrise(new BigDecimal("24.3"));
            first_teacher.setBestSeller(true);
            first_teacher.setDiscount(new BigDecimal("3"));
            first_teacher.setDescription("This book is the best book i had ever read!");
            first_teacher.setTypeOfBook(TypeOfBook.PAPER_BOOK);
            first_teacher.setPaperBook(paperBook);

            //book-asia_lion

            Book asia_lion = new Book();
            asia_lion.setImages(Arrays.asList(image5));
            asia_lion.setBookName("Азия арстаны");
            asia_lion.setAuthorFullName("Нуржигит Кадырбеков");
            asia_lion.setGenre(genre5);
            asia_lion.setPublishingHouseName("Frunze"); //test
            asia_lion.setLanguage(Language.KYRGYZ);
            asia_lion.setDateOfIssue(LocalDate.of(2019, Month.JULY, 18));
            asia_lion.setPageSize(212);
            asia_lion.setPrise(new BigDecimal("24.3"));
            asia_lion.setBestSeller(true);
            asia_lion.setDiscount(new BigDecimal("4"));
            asia_lion.setDescription("This book is the best book i had ever read!");
            asia_lion.setTypeOfBook(TypeOfBook.ELECTRONIC_BOOK);
            asia_lion.setElectronicBook(electronicBook2);

            //book-alykul_osmonov

            Book alykul_osmonov = new Book();
            alykul_osmonov.setImages(Arrays.asList(image2));
            alykul_osmonov.setBookName("Алыкул Осмонов");
            alykul_osmonov.setAuthorFullName("Мундузбек Тентимишев");
            alykul_osmonov.setGenre(genre1);
            alykul_osmonov.setPublishingHouseName("Globus"); //test
            alykul_osmonov.setLanguage(Language.KYRGYZ);
            alykul_osmonov.setDateOfIssue(LocalDate.of(2016, Month.JULY, 18));
            alykul_osmonov.setPageSize(596);
            alykul_osmonov.setPrise(new BigDecimal("1400"));
            alykul_osmonov.setBestSeller(true);
            alykul_osmonov.setDiscount(new BigDecimal("1"));
            alykul_osmonov.setDescription("This book is the best book i had ever read!");
            alykul_osmonov.setTypeOfBook(TypeOfBook.AUDIO_BOOK);
            alykul_osmonov.setAudioBook(audioBook);

            //book-manas

            Book manas = new Book();
            manas.setImages(Arrays.asList(image3));
            manas.setBookName("Манас");
            manas.setAuthorFullName("Редекция жамааты");
            manas.setGenre(genre3);
            manas.setPublishingHouseName("Frunze"); //test
            manas.setLanguage(Language.RUSSIAN);
            manas.setDateOfIssue(LocalDate.of(2010, Month.JULY, 18));
            manas.setPageSize(212);
            manas.setPrise(new BigDecimal("1500"));
            manas.setBestSeller(true);
            manas.setDiscount(new BigDecimal("90"));
            manas.setDescription("This book is the best book i had ever read!");
            manas.setTypeOfBook(TypeOfBook.ELECTRONIC_BOOK);
            manas.setElectronicBook(electronicBook);

            //selectedBooks

            SelectedBooks selectedBooks = new SelectedBooks();
            selectedBooks.setBooks(Arrays.asList(first_teacher));
            selectedBooks.setQuantityOfBooks(2);

            //selectedBooks2

            SelectedBooks selectedBooks2 = new SelectedBooks();
            selectedBooks2.setBooks(Arrays.asList(educated));
            selectedBooks2.setQuantityOfBooks(3);

            //selectedBooks3

            SelectedBooks selectedBooks4 = new SelectedBooks();
            selectedBooks4.setBooks(Arrays.asList(alykul_osmonov));
            selectedBooks4.setQuantityOfBooks(9);

            //selectedBooks4

            SelectedBooks selectedBooks6 = new SelectedBooks();
            selectedBooks6.setBooks(List.of(asia_lion));
            selectedBooks6.setQuantityOfBooks(6);

            //basket1

            Basket basket = new Basket();
            basket.setBooks(List.of(educated));
            basket.setQuantityOfBooks(32);

            //basket2

            Basket basket2 = new Basket();
            basket2.setBooks(Arrays.asList(alykul_osmonov));
            basket2.setQuantityOfBooks(30);

            //basket3

            Basket basket3 = new Basket();
            basket3.setBooks(Arrays.asList(asia_lion));
            basket3.setQuantityOfBooks(6);

            //basket4

            Basket basket4 = new Basket();
            basket4.setBooks(Arrays.asList(manas));
            basket4.setQuantityOfBooks(30);

            //Promo

            Promo promo = new Promo();
            promo.setPromoName("manas");
            promo.setStartingDay(LocalDate.of(2022, Month.JANUARY, 12));
            promo.setFinishingDay(LocalDate.of(2022, Month.FEBRUARY, 12));
            promo.setPercent((byte) 2);
            promo.setBooks(Arrays.asList(manas));

            //Promo2

            Promo promo2 = new Promo();
            promo2.setPromoName("first_teacher");
            promo2.setStartingDay(LocalDate.of(2022, Month.JANUARY, 12));
            promo2.setFinishingDay(LocalDate.of(2022, Month.FEBRUARY, 11));
            promo2.setPercent((byte) 5);
            promo2.setBooks(Arrays.asList(first_teacher));

            //Authentication-admin

            AuthenticationInfo admin = new AuthenticationInfo();
            admin.setAuthority(Authority.ADMIN);
            admin.setEmail("admin@gmail.com");
            admin.setPassword("$2a$12$wEjFI2VxIoxzeI8qi34upetbXSmMmG5jpe75bH2LK/a19yfRtVuVe\n");
            admin.setAccountNonLocked(true);
            admin.setAccountNonExpired(true);
            admin.setCredentialsNonExpired(true);
            admin.setEnabled(true);

            //Authentication-client

            AuthenticationInfo client = new AuthenticationInfo();
            client.setAuthority(Authority.CLIENT);
            client.setEmail("client@gmail.com");
            client.setPassword("$2a$12$FkNwQ4mn/D.cJnDggDKdlOWXvmeMsjZdQrNRy9euazS.kCsz2t8.W\n");
            client.setAccountNonLocked(true);
            client.setAccountNonExpired(true);
            client.setCredentialsNonExpired(true);

            //Authentication-client-2

            AuthenticationInfo client2 = new AuthenticationInfo();
            client2.setAuthority(Authority.CLIENT);
            client2.setEmail("client2@gmail.com");
            client2.setPassword("$2a$12$kIBBhgDUqUdJ.JryO24ni.G0PSBXZGrLqTdAV.D9lhg/2itLU9QBm\n");
            client2.setAccountNonLocked(true);
            client2.setAccountNonExpired(true);
            client2.setCredentialsNonExpired(true);
            client2.setEnabled(true);

            //Authentication-vendor

            AuthenticationInfo vendor1 = new AuthenticationInfo();
            vendor1.setAuthority(Authority.VENDOR);
            vendor1.setEmail("vendor@gmail.com");
            vendor1.setPassword("$2a$12$uS03fdggu.Qcy2mQaegv4ehqlIx5frePwYfP444ysCWm0gtbtlpCe\n");
            vendor1.setAccountNonLocked(true);
            vendor1.setAccountNonExpired(true);
            vendor1.setCredentialsNonExpired(true);
            vendor1.setEnabled(true);

            //Authentication-vendor2

            AuthenticationInfo vendor_Role = new AuthenticationInfo();
            vendor_Role.setAuthority(Authority.VENDOR);
            vendor_Role.setEmail("vendor2@gmail.com");
            vendor_Role.setPassword("$2a$12$G9Afbhk5KyK9/4wq/1FEbejyejHgdxd2Lu4/pujPFaTjJk90/FY9.\n");
            vendor_Role.setAccountNonLocked(true);
            vendor_Role.setAccountNonExpired(true);
            vendor_Role.setCredentialsNonExpired(true);
            vendor_Role.setEnabled(true);

            // Admin

            Admin admin1 = new Admin();
            admin1.setFirstName("admin");
            admin1.setLastName("admin");
            admin1.setEmail("admin@gmail.com");
            admin1.setAuthenticationInfo(admin);



            //address

            Address address = new Address();
            address.setCountry(Country.UNITED_KINGDOM);
            address.setCityName("Osh");
            address.setAddress("home-5");
            address.setPostCode("N28-34");

            //address-2

            Address address2 = new Address();
            address2.setCountry(Country.RUSSIA);
            address2.setCityName("Nookat");
            address2.setAddress("vostok-5");
            address2.setPostCode("N28-34");

            // Vendor-Hadicha

            Vendor vendor = new Vendor();
            vendor.setFirstName("Hadicha");
            vendor.setLastName("Hadicha");
            vendor.setEmail("hadicha@gmail.com");
            vendor.setAddress(address);
            vendor.setPhoneNumber("996773015901");
            vendor.setNameOfBranch("Bishkek");
            vendor.setBooksToSale(Arrays.asList(manas));
            vendor.setAuthenticationInfo(vendor1);
            vendor.setPromoCodes(Arrays.asList(promo));

            // Vendor2-Beksultan

            Vendor vendor2 = new Vendor();
            vendor2.setFirstName("Beksultan");
            vendor2.setLastName("Beka");
            vendor2.setEmail("beksultan@gmail.com");
            vendor2.setAddress(address2);
            vendor2.setPhoneNumber("996773015901");
            vendor2.setNameOfBranch("Nookat");
            vendor2.setBooksToSale(Arrays.asList(first_teacher));
            vendor2.setAuthenticationInfo(vendor_Role);
            vendor2.setPromoCodes(Arrays.asList(promo2));

            // Client-maria

            Client maria = new Client();
            maria.setName("Maria");
            maria.setEmail("maria@gmail.com");
            maria.setSubscriptionToNewsLetter(true);
            maria.setBasket(basket);
            maria.setSelectedBooks(selectedBooks2);
            maria.setAuthenticationInfo(client);

            // Client-elnura

            Client elnura = new Client();
            elnura.setName("Elnura");
            elnura.setEmail("elnura@gmail.com");
            elnura.setSubscriptionToNewsLetter(false);
            elnura.setBasket(basket3);
            elnura.setSelectedBooks(selectedBooks6);
            elnura.setAuthenticationInfo(client2);

            //save-admin
            System.out.println(adminRepository.save(admin1));
            //save-vendors
            System.out.println(vendorRepository.save(vendor));
            System.out.println(vendorRepository.save(vendor2));

            //save-clients
            System.out.println(clientRepository.save(maria));
            System.out.println(clientRepository.save(elnura));




        };
    }
}

