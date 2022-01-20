package kg.ebooks.eBook.aws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 20/1/22
 * Thursday 16:17
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileException extends RuntimeException {

    public InvalidFileException() {
    }

    public InvalidFileException(String message) {
        super(message);
    }
}
