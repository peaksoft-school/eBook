package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

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


    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('VENDOR')")
    @PostMapping("/create")
    @Operation( summary = "create new promo", description = "this POST method to save Promocodes, you should write promo-name" +
            ", starting & finishing days, percent correctly, if you don't write correctly the method returns 400 and message " +
            " AND You can create promo between NOW and NOW + 1 year")
    public String createPromo(Authentication authentication, @Valid @RequestBody PromoCreate promo) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return promoService.createPromo(authenticationInfo.getEmail(), promo);
    }

//    public List<?> findPromo() {
//
//    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

}
