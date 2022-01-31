package kg.ebooks.eBook.db.service.impl;


import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.exceptions.ClientNotFoundException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final SignupRequestClntMapper clientMapper;

    @Override
    public SignupRequestClnt registerClient(SignupRequestClnt signupRequest) {
        String email = signupRequest.getEmail();
        log.info("Saving new user {} to the database", email);

        Optional<AuthenticationInfo> byEmail =
                authenticationInfoRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new IllegalStateException(
                    "user with email = " + email + "has already exists"
            );
        }

        Client client = SignupRequestClntMapper.makeClient(signupRequest);
        client.getAuthenticationInfo().setPassword(
                passwordEncoder.encode(client.getAuthenticationInfo().getPassword())
        );
        return SignupRequestClntMapper.makeSignupRequestsClnt(clientRepository.save(client));
    }


    @Override
    public List<ClientDto> getClients(Client client) {
        log.info("ClientController  - getClients -: {}", client);
        return clientRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    public ClientDto clientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(String.valueOf(client.getClientId()));
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
    public ClientDto saveClient(ClientDto clientDto) {
        Optional<ClientDto> empty = clientRepository.findByEmail(clientDto.getEmail());
        Client client = clientMapper.makeClient2(clientDto);
        log.info("create clients service + {} " + clientDto);

        if (empty.isEmpty()) {
            clientRepository.save(client);
        } else {
            throw new ClientNotFoundException(" not found exception ");
        }
        return clientDto;
    }

    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientDto client) {
        Client clientFromDatabase = clientRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException(
                        "client with id = " + id + " does not exists"
                ));
        clientFromDatabase.setName(client.getName());
        clientFromDatabase.setEmail(client.getEmail());
        clientFromDatabase.getAuthenticationInfo().setPassword(

                passwordEncoder.encode(client.getPassword())
        );
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
