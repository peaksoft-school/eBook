package kg.ebooks.eBook.db.domain.dto.client;

import kg.ebooks.eBook.annotations.password.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/3/22
 * Sunday 21:26
 * hello world
 */
@Data
public class ClientUpdateRequest {

    @NotBlank(message = "incorrect name")
    private String name;

    @Email(message = "you give incorrect email")
    private String email;

    @Password
    private String currentPassword;

    @Password
    private String newPassword;
}
