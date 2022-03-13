package kg.ebooks.eBook.db.domain.dto.vendor;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.ebooks.eBook.annotations.password.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Beksultan
 */
@Getter
@Setter
public class VendorUpdateDto {
    @NotBlank(message = "first name should not be null or empty")
    private String firstName;
    @NotBlank(message = "last name should not be null or empty")
    private String lastName;
    @NotBlank(message = "phone number should not be null or empty")
    private String phoneNumber;
    @Email(message = "email shoule be valid")
    private String email;
    @Password(message = "current password should be valid")
    private String currentPassword;
    @Password(message = "new password should be valid")
    private String newPassword;
}
