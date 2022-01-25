package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.SignupRequestVndr;

public interface VendorService {

    SignupRequestVndr registerVendor(SignupRequestVndr signupRequest);
}
