package kg.ebooks.eBook.exceptions.handler;

import com.google.gson.JsonObject;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.InvalidPasswordException;
import kg.ebooks.eBook.exceptions.InvalidPromoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by Beksultan Mamatkadyr uulu
 * 4/3/22
 * Friday 19:47
 * hello world
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(InvalidPasswordException.class)
    public String handleConflict(InvalidPasswordException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidPromoException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConflict(InvalidPromoException e) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", e.getMessage());
        String asString = jsonObject.getAsString();
        System.out.println(asString);
        return asString;
    }
}
