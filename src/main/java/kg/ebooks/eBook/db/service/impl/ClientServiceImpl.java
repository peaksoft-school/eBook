package kg.ebooks.eBook.db.service.impl;


import kg.ebooks.eBook.db.domain.dto.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public SignupRequestClnt registerClient(SignupRequestClnt signupRequest) {
        Optional<Client> userByEmail = clientRepository.findUserByEmail(signupRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException(
                    "user with email = " + signupRequest.getEmail() + " has already exists"
            );
        }

        Client client = SignupRequestClntMapper.makeClient(signupRequest);
        return SignupRequestClntMapper.makeSignupRequestsClnt(clientRepository.save(client));
    }
}
