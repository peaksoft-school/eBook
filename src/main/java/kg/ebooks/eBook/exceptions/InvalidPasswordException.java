package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Beksultan
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
