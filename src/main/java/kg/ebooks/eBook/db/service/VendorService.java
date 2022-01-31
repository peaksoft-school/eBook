package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;

public interface VendorService {

    SignupRequestVndr registerVendor(SignupRequestVndr signupRequest);

//    List<VendorDto> getAllVendors(Vendor vendor);

    VendorDto updateVendor(Long id, VendorDto vendorDto);
    void deleteVendor(Long id);
}
