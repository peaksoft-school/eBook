package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.others.Basket;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.BasketService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 25/1/22
 * Tuesday 17:01
 */
@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public void addBookToBasket(String clientEmail, Long bookId) {
        Book book = getOrElseThrow(bookId);

        Client client = getClientByEmail(clientEmail);

        if (!book.getTypeOfBook().equals(TypeOfBook.PAPER_BOOK)) {
            if (client.getBasket().getBooks().contains(book)) {
                throw new AlreadyExistsException(
                        "book [ " + book.getBookName() + " ] is already in a basket"
                );
            }
        }

        if (!client.getBasket().getBooks().contains(book)) {
            book.incrementInBasket();
        }

        client.getBasket().setBook(book);
    }

    @Override
    @Transactional
    public void deleteBookFromBasket(String clientEmail, Long bookId) {

        Book book = getOrElseThrow(bookId);

        Client client = getClientByEmail(clientEmail);

        if (!client.getBasket().getBooks().contains(book)) {
            throw new DoesNotExistsException(
                    "book with id = " + bookId + " does not exists in basket of " + client.getName()
            );
        }

        client.getBasket().deleteBook(book);
    }

    @Override
    public BasketInfo getBasketByClientId(String clientEmail) {
        Client client = getClientByEmail(clientEmail);
        Basket basket = client.getBasket();
        return basket.makeBasketInfo();
    }

    private Book getOrElseThrow(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "Book with id = " + bookId + " does not exists"
                ));
    }

    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new DoesNotExistsException(
                        "Client with id = " + clientId + " does not exists"
                ));
    }

    private Client getClientByEmail(String clientEmail) {
        return clientRepository.findByEmailO(clientEmail)
                .orElseThrow(() -> new DoesNotExistsException(
                        "Client with email = " + clientEmail + " does not exists"
                ));
    }

    @Override
    @Transactional
    public void cleanBasketByClientId(String clientEmail) {
        Client client = getClientByEmail(clientEmail);
        client.getBasket().clear();
    }
}
