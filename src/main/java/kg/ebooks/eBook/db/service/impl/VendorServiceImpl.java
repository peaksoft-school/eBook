package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.ClientNotFoundException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper.makeSignupRequestsVndr;
import static kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper.makeVendor;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final SignupRequestVndrMapper vendorMapper;

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


    @Override
    public List<VendorDto> getAllVendors(Vendor vendor) {
        log.info("ClientController  - getClients -: {}", vendor);
        return vendorRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public VendorDto clientDto(Vendor vendor) {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
//        vendorDto.setNameOfBranch(vendor.getNameOfBranch());
//        vendorDto.setPassword(vendor.getAuthenticationInfo().getPassword());
        return vendorDto;
    }

    @Override
    public Vendor getByIdVendor(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> {
                    ClientNotFoundException notFoundException = new ClientNotFoundException(
                            "client with id  " + id + "  not found");
                    log.error("error in getting client {}", id, notFoundException);
                    return notFoundException;
                });

    }


    @Transactional
    @Override
    public Vendor saveVendor(VendorDto vendorDto) {
        Optional<Vendor> optionalVendor = vendorRepository.findUserByEmail(vendorDto.getEmail());
        if (optionalVendor.isPresent()) {
            log.error("vendor with email {} has already exists", vendorDto.getEmail());
            throw new AlreadyExistsException(
                    "vendor with email = " + vendorDto.getEmail() + " has already exists"
            );
        }
        Vendor vendor = vendorMapper.vendorMapper(vendorDto);
        System.out.println(vendor);
        log.info("create clients service + {} " + vendorDto);
        Vendor vendorSave = vendorRepository.save(vendor);
        return vendorSave;
    }


    @Override
    @Transactional
    public VendorDto updateVendor(Long id, VendorDto vendorDto) {
        System.out.println("ruslan");
        Vendor vendorFromDataBase = vendorRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException(
                        "vendor with id = " + id + " does not exists"
                ));
        Optional<Vendor> optionalVendor = vendorRepository.findUserByEmail(vendorDto.getEmail());
        if (optionalVendor.isPresent()) {
            log.error("vendor with email {} has already exists", vendorDto.getEmail());
            throw new AlreadyExistsException(
                    "vendor with email = " + vendorDto.getEmail() + " has already exists"
            );
        }

        vendorFromDataBase.setFirstName(vendorDto.getFirstName());
        vendorFromDataBase.setLastName(vendorDto.getLastName());
        vendorFromDataBase.setPhoneNumber(vendorDto.getPhoneNumber());
        vendorFromDataBase.setEmail(vendorDto.getEmail());

        vendorFromDataBase.getAuthenticationInfo()
                .setPassword((vendorDto.getPassword()));

        return vendorDto;
    }

    @Override
    public void deleteVendor(Long id) {
        this.vendorRepository.deleteById(id);
    }

}
