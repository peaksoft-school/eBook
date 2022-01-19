package kg.ebooks.eBook;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.AdminRepository;
//import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@OpenAPIDefinition
public class EBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBookApplication.class, args);
        System.out.println("DON'T WORRY THIS IS JUST A TESTK");
//        ModelMapper mapper = new ModelMapper();

//        Client client = new Client();
//        ClientDTO clie = mapper.map(client, ClientDTO.class);
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

//        Client client = new Client(
//                null,
//                "Ruslan",
//                "ruslan@gmail.com",
//                true,
//                "basket",
//                "selected books",
//                "authenticationInfo"
//
//        );
//

    }
}
