package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class SignupRequestClntMapper {

    private final PasswordEncoder passwordEncoder;

    public static Client makeClient(SignupRequestClnt signupRequestClnt) {

        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(signupRequestClnt.getEmail());
        authenticationInfo.setPassword(signupRequestClnt.getPassword());
        authenticationInfo.setAuthority(Authority.CLIENT);

        Client client = new Client();
        client.setName(signupRequestClnt.getName());
        client.setEmail(signupRequestClnt.getEmail());
        client.setAuthenticationInfo(authenticationInfo);
        return client;
    }

    public static SignupRequestClnt makeSignupRequestsClnt(Client client) {
        SignupRequestClnt signupRequestClnt = new SignupRequestClnt();
        signupRequestClnt.setName(client.getName());
        signupRequestClnt.setEmail(client.getEmail());
        signupRequestClnt.setPassword(client.getAuthenticationInfo().getPassword());
        return signupRequestClnt;
    }


    @Transactional
    public Client makeClient2(ClientDto clientDto) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(clientDto.getEmail());
        authenticationInfo.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        authenticationInfo.setAuthority(Authority.CLIENT);

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setAuthenticationInfo(authenticationInfo);
        return client;
    }


    public ClientDto clientGetById(Client client) {
        if (client == null) {
            return null;
        }
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(String.valueOf(client.getClientId()));
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return clientDto;
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return new ModelMapper();
    }
}
