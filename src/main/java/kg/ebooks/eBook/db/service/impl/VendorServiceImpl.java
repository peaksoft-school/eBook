package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorBook;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.mapper.VendorMapper;
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
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<VendorDto> getAllVendors(Vendor vendor) {
        log.info("ClientController  - getClients -: {}", vendor);
        return vendorRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorDto> getAllbook(Vendor vendor) {

        log.info("ClientController  - getClients -: {}", vendor);
        return vendorRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    public VendorDto clientDto1(Vendor vendor) {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setEmail(vendor.getEmail());
//        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return vendorDto;

    } public VendorDto clientDto(Vendor vendor) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(bookDTO.getAuthor());
        bookDTO.setBookName(bookDTO.getBookName());
        bookDTO.setDiscount(bookDTO.getDiscount());
        bookDTO.setNetPrice(bookDTO.getNetPrice());
        bookDTO.setDiscountedPrice(bookDTO.getDiscountedPrice());
        bookDTO.setImage(bookDTO.getImage());
//
        VendorDto vendorDto = new VendorDto();
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setBookDTOList(Arrays.asList(bookDTO));
//        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return vendorDto;

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
    @Override
    public VendorBook saveBook(VendorBook vendorBook) {
    Vendor vendor = VendorMapper.makeVendor(vendorBook);
    vendorRepository.save(vendor);
    return vendorBook;
    }
}
