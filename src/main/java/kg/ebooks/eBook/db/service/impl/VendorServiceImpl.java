package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupRequestVndr registerVendor(SignupRequestVndr signupRequest) {
        log.info("Saving new user {} to the database", signupRequest.getEmail());
        Optional<Vendor> userByEmail = vendorRepository.findUserByEmail(signupRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException(
                    "vendor with email = " + signupRequest.getEmail() + " has already exists"
            );
        }
        Vendor vendor = makeVendor(signupRequest);
        vendor.getAuthenticationInfo().setPassword(passwordEncoder.encode(vendor.getAuthenticationInfo().getPassword()));
        return makeSignupRequestsVndr(vendorRepository.save(vendor));
    }
}
