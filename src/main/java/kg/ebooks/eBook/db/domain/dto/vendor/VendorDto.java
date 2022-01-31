package kg.ebooks.eBook.db.domain.dto.vendor;

import lombok.Data;

@Data
public class VendorDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
