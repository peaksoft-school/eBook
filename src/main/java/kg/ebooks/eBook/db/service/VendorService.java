package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;

public interface VendorService {

    SignupRequestVndr registerVendor(SignupRequestVndr signupRequest);
}
