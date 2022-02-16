package kg.ebooks.eBook.db.domain.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import kg.ebooks.eBook.annotations.password.Password;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

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
    @Password(message = "senin parolun kata")
    private String password;


}
