package kg.ebooks.eBook;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition
@RequiredArgsConstructor
public class EBookApplication {

//    private final AdminRepository adminRepository;
//
//    @PostConstruct
//    public void guest(){
//        AuthenticationInfo auth = new AuthenticationInfo(
//                null,
//                Authority.ADMIN,
//                "elnura",
//                "elnura",
//                true,
//                true,
//                true,
//                true
//        );
//
//        Admin admin = new Admin(
//                null,
//                "Elnura",
//                "Tadzhibaeva",
//                "elnura@gmail.com",
//                null,
//                auth
//        );
//        adminRepository.save(admin);
//    }


    public static void main(String[] args) {
        SpringApplication.run(EBookApplication.class, args);
        System.out.println("Welcome to eBook Application!");
    }
}

