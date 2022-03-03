//package kg.ebooks.eBook.db.service.impl;
//
//import kg.ebooks.eBook.db.domain.model.users.Client;
//import kg.ebooks.eBook.db.repository.ClientRepository;
//import kg.ebooks.eBook.db.service.ClientService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class ClientServiceImplTest {
//    @Autowired
//    private ClientService clientService;
//    @MockBean
//    private ClientRepository clientRepository;
//
//    @Test
//    void getClients() {
//        when(clientRepository.findAll()).thenReturn(Stream.
//                of(new Client(1l, "ruslan", "ruslan@gmail.com")
//                        , new Client(2l, "alex", "alex@gmail.com")).collect(Collectors.toList()));
//        assertEquals(2, clientService.getClients().size());
//
//
//    }
//
//    @Test
//    void getClientById() {
//
//    }
//
//    @Test
//    void updateClient() {
//
//    }
//
//    @Test
//    void deleteClientById() {
//      Optional<Client> client =Optional.ofNullable(new Client(1l, "ruslan", "ruslan@gmail.com"));
//      when(clientRepository.findById(1l)).thenReturn(client);
//        assertEquals(1, clientService.getClientById(1l).getClientId());
//
//    }
//
//}