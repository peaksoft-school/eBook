package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResponse;
import kg.ebooks.eBook.db.domain.dto.client.ClientUpdateRequest;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface ClientService {

    SignupRequestClnt registerClient(SignupRequestClnt signupRequest);

    List<ClientDtoResponse> getClients();

    Client getClientById(Long id);

    ClientDto updateClient(Long id, ClientUpdateRequest client);

    void deleteClientById(Long id);

    ClientDtoResponse getInfo(String name);

    String makeAPurchase(String email) throws MessagingException, IOException;
}
