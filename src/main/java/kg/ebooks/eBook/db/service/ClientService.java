package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.model.users.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client saveClient(Client client);
}
