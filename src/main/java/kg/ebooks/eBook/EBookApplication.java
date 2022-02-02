package kg.ebooks.eBook;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.Admin;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

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
        System.out.println("DON'T WORRY THIS IS JUST A TEST");
    }
}

