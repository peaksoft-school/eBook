package kg.ebooks.eBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyListException extends RuntimeException {
    public EmptyListException() {
    }

    public EmptyListException(String message) {
        super(message);
    }
}
