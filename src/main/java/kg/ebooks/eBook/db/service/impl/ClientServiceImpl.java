package kg.ebooks.eBook.db.service.impl;


import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResponse;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.ClientNotFoundException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    private final ModelMapper modelMapper;
    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final SignupRequestClntMapper clientMapper;


    @Override
    public SignupRequestClnt registerClient(SignupRequestClnt signupRequest) {
        String email = signupRequest.getEmail();
        log.info("Saving new user {} to the database", email);

        Optional<AuthenticationInfo> byEmail =
                authenticationInfoRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new AlreadyExistsException(
                    "user with email = " + email + "has already exists"
            );
        }

        Client client = SignupRequestClntMapper.makeClient(signupRequest);
        client.setDateOfRegistration(LocalDate.now());
        client.getAuthenticationInfo().setPassword(
                passwordEncoder.encode(client.getAuthenticationInfo().getPassword())
        );
        return SignupRequestClntMapper.makeSignupRequestsClnt(clientRepository.save(client));
    }


    @Override
    public List<ClientDtoResponse> getClients() {
        log.info("ClientController  - getClients -: {}");
        return clientRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    public ClientDtoResponse clientDto(Client client) {
        ClientDtoResponse clientDto = new ClientDtoResponse();
        clientDto.setClientId(client.getClientId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
//        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return clientDto;
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> {
                    ClientNotFoundException notFoundException = new ClientNotFoundException(
                            "client with id  " + id + "  not found");
                    log.error("error in getting client {}", id, notFoundException);
                    return notFoundException;
                });
    }


    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client clientFromDatabase = clientRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException(
                        "client with id = " + id + " does not exists"
                ));
        Optional<Client> clientOptional = clientRepository.findUserByEmail(clientDto.getEmail());
        if (clientOptional.isPresent()) {
            log.error("client with email {} has already exists", clientDto.getEmail());
            throw new AlreadyExistsException(
                    "client with email = " + clientDto.getEmail() + " has already exists"
            );
        }
        clientFromDatabase.setName(clientDto.getName());
        clientFromDatabase.setEmail(clientDto.getEmail());
        clientFromDatabase.getAuthenticationInfo().setPassword(
                passwordEncoder.encode(clientDto.getPassword()));

        clientRepository.save(clientFromDatabase);
        return clientDto;
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

    @Override
    public ClientDtoResponse getInfo(String email) {
        Client client = clientRepository.findUserByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("client with email = %s does not exists", email)
                ));
        return modelMapper.map(client, ClientDtoResponse.class);
    }
}

