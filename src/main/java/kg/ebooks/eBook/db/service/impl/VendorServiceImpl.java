package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor saveVendor(Vendor vendor) {
        log.info("Saving new user {} to the database", vendor.getEmail());
        vendor.getAuthenticationInfo().setPassword(passwordEncoder.encode(vendor.getAuthenticationInfo().getPassword()));
        return vendorRepository.save(vendor);

    }
}
