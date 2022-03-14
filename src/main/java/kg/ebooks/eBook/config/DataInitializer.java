package kg.ebooks.eBook.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import kg.ebooks.eBook.aws.bucket.FolderName;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.repository.FileRepository;
import kg.ebooks.eBook.aws.service.FileService;
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
import org.apache.http.entity.ContentType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;
import static com.amazonaws.util.IOUtils.toByteArray;

@Component
public class DataInitializer {


    @Bean
//    @Transactional
    CommandLineRunner commandLineRunner(
            AdminRepository adminRepository,
            ClientRepository clientRepository,
            VendorRepository vendorRepository,
            PasswordEncoder passwordEncoder,
            GenreRepository genreRepository,
            BookRepository bookRepository,
            FileService fileService,
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

            File daVinciCode = new File("images/davincicode.png");
            File atomicHabits = new File("images/atomic-habits-dots.png");
            File electronicBookFiles = new File("images/electronicBooks.png");

            FileInputStream daVinciCodeFileInputStream = new FileInputStream(daVinciCode);
            FileInputStream atomicHabitsFileInputStream = new FileInputStream(atomicHabits);
            FileInputStream electronicBookFileInputStream = new FileInputStream(electronicBookFiles);

            MultipartFile daVinciImage = new MockMultipartFile(
                    "file",
                    daVinciCode.getName(),
                    ContentType.IMAGE_PNG.getMimeType(),
                    toByteArray(daVinciCodeFileInputStream)
            );

            MultipartFile atomicHabitsImage = new MockMultipartFile(
                    "file",
                    atomicHabits.getName(),
                    ContentType.IMAGE_PNG.getMimeType(),
                    toByteArray(atomicHabitsFileInputStream)
            );

            MultipartFile electronicBooksImage = new MockMultipartFile(
                    "file",
                    electronicBookFiles.getName(),
                    ContentType.IMAGE_PNG.getMimeType(),
                    toByteArray(electronicBookFileInputStream)
            );

            //audio book
            Book audioBook = new Book();
            HashSet<FileInfo> images2 = Sets.newHashSet(
                    fileService.uploadFile(FolderName.IMAGES, daVinciImage),
                    fileService.uploadFile(FolderName.IMAGES, daVinciImage),
                    fileService.uploadFile(FolderName.IMAGES, daVinciImage)
            );
            fileRepository.saveAll(images2);

            audioBook.setImages(images2);
            audioBook.setBookName("The Da Vinci Code");
            audioBook.setAuthor("Dan Brown");
            audioBook.setGenre(genre);
            audioBook.setDescription("this is audio book description");
            audioBook.setLanguage(Language.ENGLISH);
            audioBook.setYearOfIssue(2013);
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
                    fileService.uploadFile(FolderName.IMAGES, electronicBooksImage),
                    fileService.uploadFile(FolderName.IMAGES, electronicBooksImage),
                    fileService.uploadFile(FolderName.IMAGES, electronicBooksImage)
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
            electronicBook.setYearOfIssue(2017);
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
                    fileService.uploadFile(FolderName.IMAGES, atomicHabitsImage),
                    fileService.uploadFile(FolderName.IMAGES, atomicHabitsImage),
                    fileService.uploadFile(FolderName.IMAGES, atomicHabitsImage)
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
            paperBook.setYearOfIssue(2020);
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
            vendor.setBooksToSale(Sets.newHashSet(paperBook, electronicBook));

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
}

