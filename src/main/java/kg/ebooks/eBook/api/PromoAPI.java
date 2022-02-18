package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.promo.PromoCreate;
import kg.ebooks.eBook.db.domain.model.others.Promo;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public String createPromo(Authentication authentication, @RequestBody PromoCreate promo) {
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return promoService.createPromo(authenticationInfo.getEmail(), promo);
    }

}
