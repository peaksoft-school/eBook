package kg.ebooks.eBook.db.domain.dto.vendor;

import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDtoResquest {
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfRegistration;
    private Set<BookResponse> vendorBooks = new HashSet<>();
}
