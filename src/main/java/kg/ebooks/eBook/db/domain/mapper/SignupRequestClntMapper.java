package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;

public class SignupRequestClntMapper {

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
}
