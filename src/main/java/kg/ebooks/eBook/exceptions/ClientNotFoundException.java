package kg.ebooks.eBook.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
@AllArgsConstructor
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String msg) {
        super(msg);
    }
}
