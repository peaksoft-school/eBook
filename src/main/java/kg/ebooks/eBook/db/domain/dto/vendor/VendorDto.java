package kg.ebooks.eBook.db.domain.dto.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Email
    @NotNull
    @JsonProperty("email")
    private String email;
    private String nameOfBranch;
    @NotNull
    @JsonProperty("password")
    @JsonIgnore
    private String password;
}
