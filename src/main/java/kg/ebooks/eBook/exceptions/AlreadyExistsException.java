package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 03:26
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
