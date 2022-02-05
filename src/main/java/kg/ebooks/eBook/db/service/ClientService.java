package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.users.Client;

import java.util.List;

public interface ClientService {

    SignupRequestClnt registerClient(SignupRequestClnt signupRequest);

    List<ClientDto> getClients(Client client);

    Client getClientById(Long id);

    Client saveClient(ClientDto clientDto);

    ClientDto updateClient(Long id, ClientDto client);

    void deleteClientById(Long id);

}
