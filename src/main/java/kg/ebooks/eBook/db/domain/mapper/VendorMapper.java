package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;

public class VendorMapper {

    public static Vendor makeVendor(VendorDto vendorDto) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        authenticationInfo.setEmail(vendorDto.getEmail());
        authenticationInfo.setPassword(vendorDto.getPassword());
        authenticationInfo.setAuthority(Authority.VENDOR);

        Vendor vendor = new Vendor();
        vendor.setFirstName(vendorDto.getFirstName());
        vendor.setLastName(vendorDto.getLastName());
        vendor.setPhoneNumber(vendorDto.getPhoneNumber());
        vendor.setEmail(vendorDto.getEmail());
        vendor.setAuthenticationInfo(authenticationInfo);
        return vendor;
    }

    public static VendorDto makeSignupRequestsVndr(Vendor vendor) {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPassword(vendor.getAuthenticationInfo().getPassword());
        return vendorDto;
    }
}