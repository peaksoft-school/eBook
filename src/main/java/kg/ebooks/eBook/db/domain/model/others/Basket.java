package kg.ebooks.eBook.db.domain.model.others;

import kg.ebooks.eBook.db.domain.dto.basket.BasketBook;
import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import kg.ebooks.eBook.db.domain.dto.basket.impl.BasketInfoImpl;
import kg.ebooks.eBook.db.domain.dto.basket.impl.BookInfoBktImpl;
import kg.ebooks.eBook.db.domain.model.books.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:32
 */
@Entity
@Table(name = "baskets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Basket implements BookCase, BasketInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @ManyToMany(cascade = MERGE)
    private List<Book> books = new ArrayList<>();

    private int quantityOfBooks;

    @Override
    public void setBook(Book book) {
        books.add(book);
        quantityOfBooks++;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public void clear() {
        books.removeIf(book -> true);
    }

    @Override
    public Set<BasketBook> getBooksBkt() {
        ModelMapper modelMapper = new ModelMapper();
        Set<BasketBook> basketBooks = new HashSet<>();

        for (Book book : books) {
            BookInfoBktImpl map = modelMapper.map(book, BookInfoBktImpl.class);
            BasketBook byBookInfoBKT = findByBookInfoBKT(basketBooks, map);
            if (byBookInfoBKT == null) {
                basketBooks.add(new BasketBook(map,1L));
            } else {
                byBookInfoBKT.increment();
            }
        }
        return basketBooks;
    }

    static BasketBook findByBookInfoBKT(Set<BasketBook> basketBooks, BookInfoBktImpl bookInfoBkt) {
        return basketBooks.stream()
                .filter(basketBook -> basketBook.getBook().equals(bookInfoBkt))
                .findFirst().orElse(null);
    }

    public BasketInfo makeBasketInfo() {
        ModelMapper modelMapper = new ModelMapper();
        Set<BasketBook> basketBooks = new HashSet<>();

        for (Book book : books) {
            BookInfoBktImpl map = modelMapper.map(book, BookInfoBktImpl.class);
            BasketBook byBookInfoBKT = findByBookInfoBKT(basketBooks, map);
            if (byBookInfoBKT == null) {
                basketBooks.add(new BasketBook(map,1L));
            } else {
                byBookInfoBKT.increment();
            }
        }

        BasketInfoImpl basketInfo = new BasketInfoImpl();
        basketInfo.setBasketId(basketId);
        basketInfo.setBooksBkt(basketBooks);
        basketInfo.setQuantityOfBooks(quantityOfBooks);
        return basketInfo;
    }
}
