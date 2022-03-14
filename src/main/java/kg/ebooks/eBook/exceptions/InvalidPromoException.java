package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Beksultan
 */
//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPromoException extends RuntimeException{

    public InvalidPromoException() {
    }

    public InvalidPromoException(String message) {
        super(message);
    }
}
