package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.db.domain.dto.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
//import kg.ebooks.eBook.db.domain.dto.JwtResponse;
//import kg.ebooks.eBook.db.domain.dto.SigninRequest;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @PostMapping("/signup/vendor")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию продавцу")
    public Vendor registerVendor(@RequestBody SignupRequestVndr signupRequest) {
        return vendorService.registerVendor(signupRequest);
    }
}