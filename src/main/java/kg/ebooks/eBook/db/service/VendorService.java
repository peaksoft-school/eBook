package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VendorService {

    SignupRequestVndr registerVendor(SignupRequestVndr signupRequest);

    List<VendorDto> getAllVendors();

    Vendor getByIdVendor(Long id);

    Vendor saveVendor(VendorDto vendorDto);

    VendorDto updateVendor(Long id, VendorDto vendorDto);

    void deleteVendor(Long id);

}
