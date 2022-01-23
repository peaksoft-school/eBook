package kg.ebooks.eBook.db.domain.dto;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String email;
    private Authority authority;

}
