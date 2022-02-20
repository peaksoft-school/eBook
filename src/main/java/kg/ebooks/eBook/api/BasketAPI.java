package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "API to use basket only for users with authority CLIENT", description = "Always use [CLIENT] token to use this methods!")
@PreAuthorize("hasAuthority('CLIENT')")
public class BasketAPI {

    private final BasketService basketService;


    @Operation(summary = "add book to basket")
    @PostMapping("/add/book/{bookId}")
    public void addBookToBasket(Authentication authentication, @PathVariable Long bookId) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.addBookToBasket(authenticationInfo.getEmail(), bookId);
    }

    @Operation(summary = "delete book from basket")
    @DeleteMapping("/delete/book/{bookId}")
    public void deleteBookFromBasket(Authentication authentication,
                                     @PathVariable Long bookId) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.deleteBookFromBasket(authenticationInfo.getEmail(), bookId);
    }

    @Operation(summary = "get basket by client token")
    @GetMapping("/get")
    public BasketInfo getBasketById(Authentication authentication) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return basketService.getBasketByClientId(authenticationInfo.getEmail());
    }

    @Operation(summary = "clean basket with token")
    @DeleteMapping("/clean")
    public void cleanBasket(Authentication authentication) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        basketService.cleanBasketByClientId(authenticationInfo.getEmail());
    }

    // TODO: 25/1/22 total amounts
}
