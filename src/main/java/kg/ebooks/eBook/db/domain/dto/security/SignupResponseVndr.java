package kg.ebooks.eBook.db.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Beksultan
 */
@Getter @Setter
public class SignupResponseVndr {
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
