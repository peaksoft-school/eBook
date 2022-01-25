package kg.ebooks.eBook;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class EBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBookApplication.class, args);
        System.out.println("DON'T WORRY THIS IS JUST A TESTK");

    }

@Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> {

            AuthenticationInfo authenticationInfo = new AuthenticationInfo();
            authenticationInfo.setAuthenticationInfoId(null);
            authenticationInfo.setAuthority(Authority.CLIENT);
            authenticationInfo.setPassword("client");
            authenticationInfo.setEmail("ruslan@gmail.com");
            authenticationInfo.setCredentialsNonExpired(true);
            authenticationInfo.setAccountNonExpired(true);
            authenticationInfo.setAccountNonLocked(true);
            authenticationInfo.setEnabled(true);


            Client client =
                    new Client(null, "hello", "hello@gmail.com");
            Client client1 =
                    new Client();
            client1.setName("client");
            client1.setEmail("ruslan@gmail.com");
            client1.setAuthenticationInfo(authenticationInfo);

            System.out.println(clientRepository.save(client));
            System.out.println(clientRepository.save(client1));
        };
    }
}
