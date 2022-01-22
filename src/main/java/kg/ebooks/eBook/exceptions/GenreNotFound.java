package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 03:23
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenreNotFound extends RuntimeException {
    public GenreNotFound() {
    }

    public GenreNotFound(String message) {
        super(message);
    }
}
