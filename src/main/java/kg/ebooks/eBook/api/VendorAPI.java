package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.config.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class VendorAPI {

    private final VendorService vendorService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @PostMapping("/signup/vendor")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию продавцу")
    public SignupRequestVndr registerVendor(@RequestBody SignupRequestVndr signupRequest) {
        return vendorService.registerVendor(signupRequest);
    }

    @PutMapping({"/update/vendor/{vendorId}"})
    public ResponseEntity<VendorDto> updateVendorProfil(@PathVariable("vendorId") Long id,
                                                        @RequestBody VendorDto vendorDto) {
        try {
            return new ResponseEntity<>(vendorService.updateVendor(id, vendorDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping({"/deleteById/vendor/{vendorId}"})
    public ResponseEntity<Void> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.ok().build();
    }
}