package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationInfoRepository authenticationInfoRepository;

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
}
