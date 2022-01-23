package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.model.users.Client;

import java.util.List;

public interface ClientService {

    SignupRequestClnt registerClient(SignupRequestClnt signupRequest);
}
