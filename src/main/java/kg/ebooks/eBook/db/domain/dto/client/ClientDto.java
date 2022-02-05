package kg.ebooks.eBook.db.domain.dto.client;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long clientId;
    private String name;
    private String email;
    private String password;
}
