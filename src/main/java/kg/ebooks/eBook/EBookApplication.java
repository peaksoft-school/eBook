package kg.ebooks.eBook;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.repository.ClientRepository;
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
	CommandLineRunner commandLineRunner (ClientRepository clientRepository) {
		return args -> {
			Client client =
					new Client(null, "ruslan", "ruslan@gmail.com");
			Client client1 =
					new Client(null, "ruslan", "ruslan@gmail.com");
			System.out.println(clientRepository.save(client));
			System.out.println(clientRepository.save(client1));
		};
	}
}
