package kg.ebooks.eBook.controller;

//import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.dto.JwtResponse;
import kg.ebooks.eBook.db.dto.SigninRequest;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api2")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/authentication")
    public ResponseEntity<?> authVendor(@RequestBody SigninRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                authenticationInfo.getEmail(),
                authenticationInfo.getAuthority()));
    }

//    @PostMapping("/register")
////    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
//    public ResponseEntity<?> saveVendor(@RequestBody Vendor vendor) {
//        try {
//            vendorService.saveVendor(vendor);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}