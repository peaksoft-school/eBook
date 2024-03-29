package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.security.SignupResponseVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResponse;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResquest;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorUpdateDto;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface VendorService {

    SignupResponseVndr registerVendor(SignupRequestVndr signupRequest);

    List<VendorDtoResponse> getAllVendors();

    VendorDtoResquest getByIdVendor(Long id);

    VendorDto updateVendor(String email, VendorUpdateDto vendorDto);

    void deleteVendor(Long id);

    VendorDto showInfo(String name);

    Set<BookResponse> getVendorBooks(String name);

    Vendor findByBookId(Book book);
}
