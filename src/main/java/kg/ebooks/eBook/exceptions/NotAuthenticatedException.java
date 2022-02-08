package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 01:39
 * hello world
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthenticatedException extends RuntimeException {
    public NotAuthenticatedException() {
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }
}
