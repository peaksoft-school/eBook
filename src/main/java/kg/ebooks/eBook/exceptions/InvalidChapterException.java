package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 22/1/22
 * Saturday 22:27
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidChapterException extends RuntimeException {

    public InvalidChapterException(String s) {
        super(s);
    }
}
