package kg.ebooks.eBook.exceptions;

public class DuplicateEntryException  extends RuntimeException{
    public DuplicateEntryException() {
        super("This email is already being used");
    }

    public DuplicateEntryException(String msg) {
        super(msg);
    }

    public DuplicateEntryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
