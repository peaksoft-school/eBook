package kg.ebooks.eBook.service;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientServiceImplTest {

    @Autowired
    private ClientRepository clientRepository;

    private ClientService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClientServiceImpl(clientRepository);
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void getClients() {

        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setAuthority(Authority.CLIENT);
        authenticationInfo.setPassword("jamila");
        authenticationInfo.setEmail("jamila@gmail.com");
        authenticationInfo.setCredentialsNonExpired(true);
        authenticationInfo.setAccountNonExpired(true);
        authenticationInfo.setAccountNonLocked(true);
        authenticationInfo.setEnabled(true);


        AuthenticationInfo authenticationInfo1 = new AuthenticationInfo();
        authenticationInfo.setAuthority(Authority.CLIENT);
        authenticationInfo.setPassword("ali");
        authenticationInfo.setEmail("ali@gmail.com");
        authenticationInfo.setCredentialsNonExpired(true);
        authenticationInfo.setAccountNonExpired(true);
        authenticationInfo.setAccountNonLocked(true);
        authenticationInfo.setEnabled(true);

        Client jamila = new Client(
                null,
                "jamila",
                "jamila@gmail.com",
                true,
                null,
                null,
                authenticationInfo
        );

        Client ali = new Client(
                null,
                "ali",
                "ali@gmail.com",
                true,
                null,
                null,
                authenticationInfo1);

        clientRepository.saveAll(Arrays.asList(jamila, ali));
        //when
        List<Client> clients = underTest.getClients();
        //then
        assertEquals(2, clients.size());
    }


    @Test
    void saveClient() {
        //given
        Client jamila = new Client(
                3l,
                "jamila",
                "jamila@gmail.com");

        clientRepository.save(jamila);
        //when
        Client actual = underTest.getClientById(1l);

        //then
        assertEquals(1l, actual.getClientId());
        assertEquals("jamila", actual.getName());
        assertEquals("jamila@gmail.com", actual.getEmail());


    }

//    @Test
//    void updateClient() {
//    }
//
//    @Test
//    void deleteClientById() {
//    }
}