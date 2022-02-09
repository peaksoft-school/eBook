package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
    @Operation(summary = "Все продавцы", description = "Позволяет получить всех продавцы из базы данных")
    public ResponseEntity<List<VendorDto>> getAll() {
        try {
            log.info("getAll vendors {}");
            return new ResponseEntity<>(vendorService.getAllVendors(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @GetMapping({"/getById/{id}"})
    @Operation(summary = "продавец(id)", description = "Позволяет получить пользователя по 'id'")
    public ResponseEntity<VendorDto> getVendor(@PathVariable("id") Long id) {
        try {
            log.info("getById {}" + id);
            return new ResponseEntity<>(signupRequestVndr.clientGetById(vendorService.getByIdVendor(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @PutMapping({"/update/{vendorId}"})
    @Operation(summary = "Обновление пользователя", description = "Позволяет обновить пользователя")
    public ResponseEntity<VendorDto> updateVendorProfil(@PathVariable("vendorId") Long id,
                                                        @RequestBody VendorDto vendorDto) {
        try {
            log.info("updateVendor profil {}" + vendorDto);
            return new ResponseEntity<>(vendorService.updateVendor(id, vendorDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/deleteById/{vendorId}"})
    @Operation(summary = "Удаление продавец", description = "Позволяет удалить пользователя")
    public ResponseEntity<Void> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendor(vendorId);
        log.info("deleteVendorById " + vendorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
    public ResponseEntity<Vendor> saveVendor(@Valid @RequestBody VendorDto vendorDto) {
        try {
            log.info("save a in bood orders" + vendorDto);
            return new ResponseEntity<>(vendorService.saveVendor(vendorDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
}