package kg.ebooks.eBook.db.domain.dto.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
}
