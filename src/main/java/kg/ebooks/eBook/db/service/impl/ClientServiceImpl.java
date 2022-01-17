package kg.ebooks.eBook.db.service.impl;


import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        log.info("Saving new user {} to the database", client.getEmail());
        client.getAuthenticationInfo().setPassword(passwordEncoder.encode(client.getAuthenticationInfo().getPassword()));
        return clientRepository.save(client);

    }
}
