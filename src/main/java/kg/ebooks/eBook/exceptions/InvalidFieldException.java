package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * 10/2/22
 * Thursday 12:11
 * hello world
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFieldException extends RuntimeException {
    public InvalidFieldException() {
    }

    public InvalidFieldException(String message) {
        super(message);
    }
}
