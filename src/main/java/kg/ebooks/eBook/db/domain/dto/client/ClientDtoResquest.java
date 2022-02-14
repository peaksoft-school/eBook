package kg.ebooks.eBook.db.domain.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResquest {
    private Long clientId;
    private String name;
    @Email
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfRegistration;
}
