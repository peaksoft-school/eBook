package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.security.SignupResponseVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResponse;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResquest;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VendorService {

    SignupResponseVndr registerVendor(SignupRequestVndr signupRequest);

    List<VendorDtoResponse> getAllVendors();

    VendorDtoResquest getByIdVendor(Long id);

    VendorDto updateVendor(String email, VendorUpdateDto vendorDto);

    void deleteVendor(Long id);

    VendorDto showInfo(String name);
}
