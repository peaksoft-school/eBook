package kg.ebooks.eBook.db.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestVndr {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}



