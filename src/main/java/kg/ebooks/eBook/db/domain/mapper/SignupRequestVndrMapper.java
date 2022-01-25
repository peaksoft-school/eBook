package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.dto.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;

public class SignupRequestVndrMapper {

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
}

