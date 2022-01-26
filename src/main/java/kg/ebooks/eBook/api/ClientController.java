package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
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
public class ClientController {

    private final VendorService vendorService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @PostMapping("/signup/client")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию покупателю")
    public SignupRequestClnt registerClient(@RequestBody SignupRequestClnt signupRequest) {
        return clientService.registerClient(signupRequest);
    }
}
