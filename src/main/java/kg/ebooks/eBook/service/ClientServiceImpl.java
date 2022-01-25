package kg.ebooks.eBook.service;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.exception.ClientNotFoundException;
import kg.ebooks.eBook.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        log.info("getClient was called" + clients);
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            ClientNotFoundException notFoundException = new ClientNotFoundException(
                                    "client with id  " + id + "  not found");
                            log.error("error in getting client {}", id, notFoundException);
                            return notFoundException;
                        });
    }

    @Override
    public Client saveClient(Client client) {
        Optional<Client> empty = clientRepository.findByEmail(client.getEmail());
        if (!empty.isEmpty()) {
            return clientRepository.save(client);
        } else {
            throw new ClientNotFoundException(" not found exception ");
        }

    }

    @Override
    public Client updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .map(client1 -> {
                    log.debug("Update Client with id " + id + " does not exists");
                    client1.setAuthenticationInfo(client.getAuthenticationInfo());
                    return clientRepository.save(client1);
                })
                .orElseGet(() -> {
                    client.setClientId(id);
                    return clientRepository.save(client);

                });
    }

    @Override
    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(
                    "Client with id " + id + " does not exists"
            );
        }
        clientRepository.deleteById(id);

    }
}
