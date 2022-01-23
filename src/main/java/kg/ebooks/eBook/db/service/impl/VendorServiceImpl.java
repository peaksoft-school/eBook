package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Vendor registerVendor(SignupRequestVndr signupRequest) {
        Optional<Vendor> userByEmail = vendorRepository.findUserByEmail(signupRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException(
                    "vendor with email = " + signupRequest.getEmail() + " has already exists"
            );
        }
        return null;
    }
}
