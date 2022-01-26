package kg.ebooks.eBook;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.ClientRepository;
import org.modelmapper.ModelMapper;
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


//    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {

        return args -> {
            AuthenticationInfo client = new AuthenticationInfo();
            client.setAuthority(Authority.CLIENT);
            client.setEmail("client@gmail.com");
            client.setPassword("$2a$12$FkNwQ4mn/D.cJnDggDKdlOWXvmeMsjZdQrNRy9euazS.kCsz2t8.W\n");
            client.setAccountNonLocked(true);
            client.setAccountNonExpired(true);
            client.setCredentialsNonExpired(true);


            Client maria = new Client();
            maria.setName("Maria");
            maria.setEmail("maria@gmail.com");
            maria.setSubscriptionToNewsLetter(true);
//            maria.setBasket(basket);
//            maria.setSelectedBooks(selectedBooks2);
            maria.setAuthenticationInfo(client);

            clientRepository.save(maria);
        };
    }
}
