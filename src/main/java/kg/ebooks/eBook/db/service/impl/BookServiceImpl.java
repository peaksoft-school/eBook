package kg.ebooks.eBook.db.service.impl;

import com.google.gson.JsonObject;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.BookService;
import kg.ebooks.eBook.exceptions.InvalidRequestException;
import kg.ebooks.eBook.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Beksultan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    @Override
    public String like(String clientEmail, Long bookId) {
        Client client = findClientByEmail(clientEmail);

        Book book = findBookById(bookId);

        if (client.isLiked(book)) {
            throw new InvalidRequestException(
                    "you have already liked this book"
            );
        }

        client.setLikedBook(book);

        book.like();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("response", "SUCCESS");
        return jsonObject.getAsString();
    }

    private Client findClientByEmail(String clientEmail) {
        return clientRepository.findUserByEmail(clientEmail)
                .orElseThrow(() -> new NotFoundException(
                        String.format("client with email = %s does not exists", clientEmail)
                ));
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("book with id = %d does not exists", bookId)
                ));
    }

    @Override
    public String dislike(String clientEmail, Long bookId) {
        Client client = findClientByEmail(clientEmail);

        Book book = findBookById(bookId);

        if (!client.isLiked(book)) {
            throw new InvalidRequestException(
                    "you have already disliked this book"
            );
        }

        client.removeFromSelectedBooks(bookId);

        book.disLike();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("response", "SUCCESS");
        return jsonObject.getAsString();
    }
}
