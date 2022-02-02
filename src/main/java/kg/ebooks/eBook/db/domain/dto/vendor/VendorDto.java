package kg.ebooks.eBook.db.domain.dto.vendor;

import kg.ebooks.eBook.db.domain.dto.book.BookDTO;
import lombok.Data;

import java.util.List;

@Data
public class VendorDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private List<BookDTO>bookDTOList;

}
