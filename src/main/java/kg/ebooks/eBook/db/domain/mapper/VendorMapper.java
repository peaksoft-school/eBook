package kg.ebooks.eBook.db.domain.mapper;

import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorBook;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.Authority;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class VendorMapper {

    public static Vendor makeVendor2(VendorBook vendorDto) {
        Book book = new Book();
        book.setImages(Arrays.asList(vendorDto.getImage()));
        book.setBookName(vendorDto.getBookName());
        return null;
    }

    public static Vendor makeVendor(VendorBook vendorBook) {
//        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
//        authenticationInfo.setEmail(vendorDto.getEmail());
//        authenticationInfo.setPassword(vendorDto.getPassword());
//        authenticationInfo.setAuthority(Authority.VENDOR);

        Book book = new Book();
        book.setBookName(vendorBook.getBookName());
        book.setAuthor(vendorBook.getAuthor());


        Vendor vendor = new Vendor();
        vendor.setBooksToSale((Arrays.asList(book)));
//        vendor.setBooksToSale();
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

    public static VendorDto mas(Vendor vendor) {
        VendorDto vendorDto = new VendorDto();
//        vendor.setBooksToSale(Arrays.asList());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(bookDTO.getAuthor());
        bookDTO.setBookName(bookDTO.getBookName());
        bookDTO.setDiscount(bookDTO.getDiscount());
        bookDTO.setNetPrice(bookDTO.getNetPrice());
        bookDTO.setDiscountedPrice(bookDTO.getDiscountedPrice());
        bookDTO.setImage(bookDTO.getImage());
//
        vendorDto.setFirstName(vendor.getFirstName());
        vendorDto.setLastName(vendor.getLastName());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPassword(vendor.getAuthenticationInfo().getPassword());
        vendorDto.setBookDTOList(Arrays.asList(bookDTO));

        return vendorDto;
    }
}