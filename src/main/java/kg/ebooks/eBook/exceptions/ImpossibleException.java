package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * 27/1/22
 * Thursday 16:29
 * hello world
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImpossibleException extends RuntimeException {
    public ImpossibleException() {
    }

    public ImpossibleException(String message) {
        super(message);
    }
}
