package kg.ebooks.eBook.db.domain.model.others;

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
import java.util.List;

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

    @ManyToMany(fetch = EAGER, cascade = {DETACH, REFRESH, MERGE, PERSIST})
    private List<Book> books;

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
    public List<BookInfoBktImpl> getBooksBkt() {
        ModelMapper modelMapper = new ModelMapper();
//        List<BookInfoBkt> bookInfoBkts = new ArrayList<>();
        List<BookInfoBktImpl> list = new ArrayList<>();
        for (Book book : books) {
            BookInfoBktImpl map = modelMapper.map(book, BookInfoBktImpl.class);
            list.add(map);
        }
        return list;
//        return books.stream().map(book -> modelMapper.map(book, BookInfoBktImpl.class)).toList();
        return null;
//        return bookInfoBkts;
    }

    public BasketInfo makeBasketInfo() {
        BasketInfoImpl basketInfo = new BasketInfoImpl();
        ModelMapper modelMapper = new ModelMapper();
        basketInfo.setBasketId(basketId);
        List<BookInfoBktImpl> bkts = new ArrayList<>();
        for (Book book : books) {
            BookInfoBktImpl map = modelMapper.map(book, BookInfoBktImpl.class);
            bkts.add(map);
        }
        basketInfo.setBooksBkt(bkts);
//        List<BookInfoBktImpl> bkts = books.stream()
//                .map(book -> modelMapper.map(book, BookInfoBktImpl.class))
//                .toList();
//        basketInfo.setBooksBkt(bkts);
        basketInfo.setQuantityOfBooks(getQuantityOfBooks());
        return basketInfo;
    }
}
