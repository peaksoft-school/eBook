package kg.ebooks.eBook.db.domain.dto.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

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
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;
}
