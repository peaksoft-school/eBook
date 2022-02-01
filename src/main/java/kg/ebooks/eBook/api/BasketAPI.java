package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import kg.ebooks.eBook.db.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 11:35
 */

@CrossOrigin
@RestController
@RequestMapping("api/basket")
@RequiredArgsConstructor
public class BasketAPI {

    private final BasketService basketService;

    @PostMapping("{clientId}/add/book/{bookId}")
    public void addBookToBasket(@PathVariable Long clientId,
                                @PathVariable Long bookId) {
        basketService.addBookToBasket(clientId, bookId);
    }

    @DeleteMapping("{clientId}/delete/{bookId}/book")
    public void deleteBookFromBasket(@PathVariable Long clientId,
                                     @PathVariable Long bookId) {
        basketService.deleteBookFromBasket(clientId, bookId);
    }

    @GetMapping("/{clientId}/get")
    public BasketInfo getBasketById(@PathVariable Long clientId) {
        return basketService.getBasketByClientId(clientId);
    }

    @DeleteMapping("/{clientId}/clean")
    public void cleanBacket(@PathVariable Long clientId) {
        basketService.cleanBasketByClientId(clientId);
    }

    // TODO: 25/1/22 total amounts
}
