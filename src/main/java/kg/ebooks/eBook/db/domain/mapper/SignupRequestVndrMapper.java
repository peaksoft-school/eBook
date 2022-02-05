package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
@Component
@AllArgsConstructor
public class SignupRequestVndrMapper {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public static Vendor makeVendor(SignupRequestVndr signupRequestVndr) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(signupRequestVndr.getEmail());
        authenticationInfo.setPassword(signupRequestVndr.getPassword());
        authenticationInfo.setAuthority(Authority.VENDOR);

        Vendor vendor = new Vendor();
        vendor.setFirstName(signupRequestVndr.getFirstName());
        vendor.setLastName(signupRequestVndr.getLastName());
        vendor.setPhoneNumber(signupRequestVndr.getPhoneNumber());
        vendor.setEmail(signupRequestVndr.getEmail());
        vendor.setAuthenticationInfo(authenticationInfo);
        return vendor;
    }

    public static SignupRequestVndr makeSignupRequestsVndr(Vendor vendor) {
        SignupRequestVndr signupRequestVndr = new SignupRequestVndr();
        signupRequestVndr.setFirstName(vendor.getFirstName());
        signupRequestVndr.setLastName(vendor.getLastName());
        signupRequestVndr.setPhoneNumber(vendor.getPhoneNumber());
        signupRequestVndr.setEmail(vendor.getEmail());
        signupRequestVndr.setPassword(vendor.getAuthenticationInfo().getPassword());
        return signupRequestVndr;
    }


    public Vendor vendorMapper(VendorDto vendorDto) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(vendorDto.getEmail());
        authenticationInfo.setPassword(passwordEncoder.encode(vendorDto.getPassword()));
        authenticationInfo.setAuthority(Authority.VENDOR);

        Vendor vendor = new Vendor();
        vendor.setLastName(vendorDto.getLastName());
        vendor.setFirstName(vendorDto.getFirstName());
        vendor.setPhoneNumber(vendorDto.getPhoneNumber());
        vendor.setEmail(vendorDto.getEmail());
//        vendor.setNameOfBranch(vendorDto.getNameOfBranch());
        vendor.setAuthenticationInfo(authenticationInfo);
        return vendor;
    }


    public VendorDto clientGetById(Vendor vendor) {
        if (vendor == null) {
            return null;
        }
        VendorDto vendorDto = new VendorDto();
        vendorDto.setVendorId(vendor.getVendorId());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());

        return vendorDto;
    }

}

