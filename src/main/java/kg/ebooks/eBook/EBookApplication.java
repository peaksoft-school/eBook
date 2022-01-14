package kg.ebooks.eBook;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.repository.AdminRepository;
import kg.ebooks.eBook.db.repository.SecurityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBookApplication.class, args);
        System.out.println("DON'T WORRY THIS IS JUST A TESTK");
    }

    @Bean
    CommandLineRunner commandLineRunner(AdminRepository adminRepository) {
        return args -> {
            AuthenticationInfo auth = new AuthenticationInfo(
                    null,
                    Authority.ADMIN,
                    "elnura",
                    "elnura",
                    true,
                    true,
                    true,
                    true
            );

            Admin admin = new Admin(
                    null,
                    "Elnura",
                    "Tadzhibaeva",
                    "elnura@gmail.com",
                    auth
            );
            adminRepository.save(admin);
        };
    }
}
