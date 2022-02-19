package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 19:38
 * hello world
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PromoNotFoundException extends RuntimeException {
    public PromoNotFoundException() {
    }

    public PromoNotFoundException(String message) {
        super(message);
    }
}
