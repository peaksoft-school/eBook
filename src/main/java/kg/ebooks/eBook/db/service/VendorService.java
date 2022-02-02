package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorBook;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.users.Vendor;

import java.util.List;

public interface VendorService {

    SignupRequestVndr registerVendor(SignupRequestVndr signupRequest);

    List<VendorDto> getAllVendors(Vendor vendor);
   List<VendorDto> getAllbook(Vendor vendor);
    VendorDto updateVendor(Long id, VendorDto vendorDto);

    void deleteVendor(Long id);

    VendorBook saveBook(VendorBook vendorBook);
}
