package kg.ebooks.eBook.service;

import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.model.users.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientServices {

    List<ClientDto> getClients(Client client);

    Client getClientById(Long id);

    Client saveClient(Client client);

    Client updateClient(Long id, Client client);

    void deleteClientById(Long id);
}
