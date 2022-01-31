package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.DuplicateEntryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final AuthenticationInfoRepository authenticationInfoRepository;

    @Override
    public SignupRequestVndr registerVendor(SignupRequestVndr signupRequest) {
        String email = signupRequest.getEmail();
        log.info("Saving new user {} to the database", email);
        Optional<AuthenticationInfo> byEmail =
                authenticationInfoRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new IllegalStateException(
                    "User with email = " + email + "has already exists"
            );
        }
        Vendor vendor = makeVendor(signupRequest);
        vendor.getAuthenticationInfo().setPassword(passwordEncoder.encode(vendor.getAuthenticationInfo().getPassword()));
        return makeSignupRequestsVndr(vendorRepository.save(vendor));
    }

    @Transactional
    @Override
    public VendorDto updateVendor(Long id, VendorDto vendorDto) {

        Vendor vendorFromDataBase = vendorRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException(
                        "vendor with id = " + id + " does not exists"
                ));
        vendorFromDataBase.setFirstName(vendorDto.getFirstName());
        vendorFromDataBase.setLastName(vendorDto.getLastName());
        vendorFromDataBase.setPhoneNumber(vendorDto.getPhoneNumber());
        vendorFromDataBase.setEmail(vendorDto.getEmail());
        vendorFromDataBase.getAuthenticationInfo().setPassword(
                passwordEncoder.encode(vendorDto.getPassword()));

        if (vendorRepository.findUserByEmail(vendorDto.getEmail()) == null) {
            try {
                vendorFromDataBase.setEmail(vendorDto.getEmail());
            } catch (Exception e) {
                throw new DuplicateEntryException();
            }
        }
        return vendorDto;
    }

    @Override
    public void deleteVendor(Long id) {
        this.vendorRepository.deleteById(id);
    }
}
