package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.config.security.password.encoder.PasswordEncode;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.config.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class VendorAPI {
    @Autowired
    private final VendorService vendorService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SignupRequestVndrMapper signupRequestVndr;

    @PostMapping("/signup/vendor")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию продавцу")
    public SignupRequestVndr registerVendor(@RequestBody SignupRequestVndr signupRequest) {
        return vendorService.registerVendor(signupRequest);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VendorDto>> getAll(Vendor vendor) {
        try {
            return new ResponseEntity<>(vendorService.getAllVendors(vendor), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping({"/getById/{id}"})
    public ResponseEntity<VendorDto> getVendor(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(signupRequestVndr.clientGetById(vendorService.getByIdVendor(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping({"/update/{vendorId}"})
    public ResponseEntity<VendorDto> updateVendorProfil(@PathVariable("vendorId") Long id,
                                                        @RequestBody VendorDto vendorDto) {
        try {
            System.out.println("ha ha ha ");
            return new ResponseEntity<>(vendorService.updateVendor(id, vendorDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/deleteById/{vendorId}"})
    public ResponseEntity<Void> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<VendorDto> saveBook(@Valid @RequestBody VendorDto vendorDto) {
        try {
            return new ResponseEntity<>(vendorService.saveVendor(vendorDto), OK);
        } catch (Exception e) {
            log.info("save a in bood orders" + vendorDto);
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }
}