package kg.ebooks.eBook.db.domain.dto.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VendorDto {
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String nameOfBranch;
    private String password;
}
