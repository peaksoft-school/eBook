package kg.ebooks.eBook.db.service;

/**
 * @author Beksultan
 */
public interface BookService {
    String like(String clientEmail, Long bookId);

    String dislike(String clientEmail, Long bookId);
}
