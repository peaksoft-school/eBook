package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoFindAll;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    SignupRequestClnt registerClient(SignupRequestClnt signupRequest);

    List<ClientDtoFindAll> getClients();

    Client getClientById(Long id);

    Client saveClient(ClientDto clientDto);

    ClientDto updateClient(Long id, ClientDto client);

    void deleteClientById(Long id);

}
