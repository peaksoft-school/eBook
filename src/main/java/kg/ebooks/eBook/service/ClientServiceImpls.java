package kg.ebooks.eBook.service;

import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.mapper.ClientMapper;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.exception.ClientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpls implements ClientServices {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpls(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<ClientDto> getClients(Client client) {
        return clientRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }
    public  ClientDto clientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return clientDto;
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
        Optional<ClientDto> empty = clientRepository.findByEmail(client.getEmail());
        if (empty.isEmpty()) {
            ClientMapper.clientDto(clientRepository.save(client));
        } else {
            throw new ClientNotFoundException(" not found exception ");
        }
        return client;
    }

    @Override
    public Client updateClient(Long id, Client client) {


        return client;
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
