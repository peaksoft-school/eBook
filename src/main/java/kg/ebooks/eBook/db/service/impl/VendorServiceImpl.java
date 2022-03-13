package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.security.SignupResponseVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResponse;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResquest;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorUpdateDto;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.VendorRepository;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper.makeVendor;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final SignupRequestVndrMapper vendorMapper;

    @Override
    public SignupResponseVndr registerVendor(SignupRequestVndr signupRequest) {
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
        vendor.setDateOfRegistration(LocalDate.now());
        return modelMapper.map(vendorRepository.save(vendor), SignupResponseVndr.class);
    }


    @Override
    public List<VendorDtoResponse> getAllVendors() {
        log.info("ClientController  - getClients -: {}");
        return vendorRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public VendorDtoResponse clientDto(Vendor vendor) {
        VendorDtoResponse vendorDto = new VendorDtoResponse();
        vendorDto.setVendorId(vendor.getVendorId());
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
        return vendorDto;
    }

    @Override
    public VendorDtoResquest getByIdVendor(Long id) {
        return modelMapper.map(vendorRepository.findById(id)
                .orElseThrow(() -> {
                    ClientNotFoundException notFoundException = new ClientNotFoundException(
                            "client with id  " + id + "  not found");
                    log.error("error in getting client {}", id, notFoundException);
                    return notFoundException;
                }), VendorDtoResquest.class);
    }

    @Override
    @Transactional
    public VendorDto updateVendor(String email, VendorUpdateDto vendorDto) {
        Vendor vendorFromDataBase = vendorRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        "vendor with email = " + email + " does not exists"
                ));

        String newFirstName = vendorDto.getFirstName();
        String currentFirstName = vendorFromDataBase.getFirstName();
        if (!Objects.equals(newFirstName, currentFirstName)) {
            vendorFromDataBase.setFirstName(newFirstName);
            log.info("vendor's first name changed from {} to {}", currentFirstName, newFirstName);
        }

        String newLastName = vendorDto.getLastName();
        String currentLastName = vendorFromDataBase.getLastName();
        if (!Objects.equals(newLastName, currentLastName)) {
            vendorFromDataBase.setLastName(newLastName);
            log.info("vendor's last name changed from {} to {}", currentLastName, newLastName);
        }

        String newPhoneNumber = vendorDto.getPhoneNumber();
        String currentPhoneNumber = vendorFromDataBase.getPhoneNumber();
        if (!Objects.equals(newPhoneNumber, currentPhoneNumber)) {
            vendorFromDataBase.setPhoneNumber(newPhoneNumber);
            log.info("vendor's phone number changed from {} to {}", currentPhoneNumber, newPhoneNumber);
        }

        String newEmail = vendorDto.getEmail();
        String currentEmail = vendorFromDataBase.getEmail();
        if (!Objects.equals(newEmail, currentEmail)) {
            vendorFromDataBase.setEmail(newEmail);
            vendorFromDataBase.getAuthenticationInfo().setEmail(newEmail);
            log.info("vendor's phone number changed from {} to {}", currentEmail, newEmail);
        }

        if (!passwordEncoder.matches(vendorDto.getCurrentPassword(), vendorFromDataBase.getAuthenticationInfo().getPassword())){
            throw new InvalidPasswordException(
                    "you should write correct current password"
            );
        }

        vendorFromDataBase.getAuthenticationInfo().setPassword(passwordEncoder.encode(vendorDto.getNewPassword()));
        return modelMapper.map(vendorFromDataBase, VendorDto.class);
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new ClientNotFoundException(
                    "Client with id " + id + " does not exists");
        }
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDto showInfo(String name) {
        return modelMapper.map(vendorRepository.findUserByEmail(name)
                .orElseThrow(() -> new ClientNotFoundException(
                        "Client with email " + name + " does not exists")), VendorDto.class);
    }
}
