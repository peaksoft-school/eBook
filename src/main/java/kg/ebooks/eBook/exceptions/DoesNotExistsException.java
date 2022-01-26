package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 00:55
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoesNotExistsException extends RuntimeException {
    public DoesNotExistsException() {
    }

    public DoesNotExistsException(String message) {
        super(message);
    }
}
