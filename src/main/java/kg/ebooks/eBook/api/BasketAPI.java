package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.basket.BasketInfo;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 23/1/22
 * Sunday 11:35
 */

@CrossOrigin("*")
@RestController
@RequestMapping("api/basket")
@RequiredArgsConstructor
public class BasketAPI {

    private final BasketService basketService;

    @PostMapping("/add/book/{bookId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public void addBookToBasket(Authentication authentication, @PathVariable Long bookId) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.addBookToBasket(authenticationInfo.getEmail(), bookId);
    }

    @DeleteMapping("/delete/book/{bookId}")
    public void deleteBookFromBasket(Authentication authentication,
                                     @PathVariable Long bookId) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.deleteBookFromBasket(authenticationInfo.getEmail(), bookId);
    }

    @GetMapping("/get")
    public BasketInfo getBasketById(Authentication authentication) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return basketService.getBasketByClientId(authenticationInfo.getEmail());
    }

    @DeleteMapping("/clean")
    public void cleanBasket(Authentication authentication) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.cleanBasketByClientId(authenticationInfo.getEmail());
    }

    // TODO: 25/1/22 total amounts
}
