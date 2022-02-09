package kg.ebooks.eBook.db.domain.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResquestResponse {
    private Long clientId;
    @JsonProperty("name")
    private String name;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    @JsonIgnore
    // TODO: 7/2/22 fix password
    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfRegistration;
}
