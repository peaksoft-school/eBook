package kg.ebooks.eBook.db.domain.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import lombok.*;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String clientId;
    @JsonProperty("name")
    private String name;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    @JsonIgnore
    private String password;
}
