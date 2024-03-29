package kg.ebooks.eBook.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.Response;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.promo.FindPromo;
import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.PromoService;
import kg.ebooks.eBook.exceptions.InvalidPromoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 19/2/22
 * Saturday 02:20
 * hello world
 */
@RestController
@RequestMapping("api/promo")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "This API to work with promo codes")
public class PromoAPI {

    private final PromoService promoService;
    private final Gson gson;

    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('VENDOR')")
    @Operation( summary = "create new promo", description = "this POST method to save Promocodes, you should write promo-name" +
            ", starting & finishing days, percent correctly, if you don't write correctly the method returns 400 and message " +
            " AND You can create promo between NOW and NOW + 1 year")
    @PostMapping("/create")
    public String createPromo(Authentication authentication, @Valid @RequestBody PromoCreate promo) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return promoService.createPromo(authenticationInfo.getEmail(), promo);
    }

    @Operation(summary = "find promo", description = "this get method to get books of promocode" +
            ", you need to use client token to use this method")
    @GetMapping("/find")
    public Set<BookResponse> findPromo(@RequestParam String promo) {
        return promoService.findPromo(promo);
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/activate")
    public String activatePromo(Authentication authentication,
                                @RequestParam String promo) {
        return promoService.activatePromo(authentication.getName(), promo);
    }

    @ExceptionHandler(InvalidPromoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConflict(InvalidPromoException e) {
        return gson.toJson(new Response(e.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

}
