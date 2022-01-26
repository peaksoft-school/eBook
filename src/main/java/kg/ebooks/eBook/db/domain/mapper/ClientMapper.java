package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.dto.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {




    public static Client makeClient(ClientDto clientDto) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(clientDto.getEmail());
        authenticationInfo.setPassword(clientDto.getPassword());
        authenticationInfo.setAuthority(Authority.CLIENT);

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setAuthenticationInfo(authenticationInfo);
        return client;
    }

    public static ClientDto clientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return clientDto;
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
