package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;

public interface ClientService {

    SignupRequestClnt registerClient(SignupRequestClnt signupRequest);
}
