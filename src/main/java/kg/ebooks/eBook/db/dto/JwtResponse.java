package kg.ebooks.eBook.db.dto;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String email;
    private Authority authority;

}
