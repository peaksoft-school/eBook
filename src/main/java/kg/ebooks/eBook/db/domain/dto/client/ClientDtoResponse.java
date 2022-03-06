package kg.ebooks.eBook.db.domain.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDtoResponse {
    private Long clientId;

    private String name;
    @Email
    private String email;

}
