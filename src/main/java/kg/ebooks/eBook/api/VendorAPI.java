package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.config.jwt.JwtUtils;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoFindAll;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResquestResponse;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestVndrMapper;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/vendor")
@Tag(name = "Контроллер для управления продавецу")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class VendorAPI {
    private final VendorService vendorService;
       private final SignupRequestVndrMapper signupRequestVndr;

    @PostMapping("/signup/vendor")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию продавцу")
    public SignupRequestVndr registerVendor(@RequestBody SignupRequestVndr signupRequest) {
        return vendorService.registerVendor(signupRequest);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Все продавцы", description = "Позволяет получить всех продавцы из базы данных")
    public ResponseEntity<List<VendorDtoFindAll>> getAll() {
        try {
            log.info("getAll vendors {}");
            return new ResponseEntity<>(vendorService.getAllVendors(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @GetMapping({"/getById/{id}"})
    @Operation(summary = "продавец(id)", description = "Позволяет получить продавецу по 'id'")
    public ResponseEntity<VendorDtoResquestResponse> getVendor(@PathVariable("id") Long id) {
        try {
            log.info("getById vendor {}" + id);
            return new ResponseEntity<>(signupRequestVndr.vendorGetById(vendorService.getByIdVendor(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @PutMapping({"/update/{vendorId}"})
    @Operation(summary = "Обновление продавецу", description = "Позволяет обновить продавецу")
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
    @Operation(summary = "Удаление продавецу", description = "Позволяет удалить продавецу")
    public ResponseEntity<Void> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendor(vendorId);
        log.info("deleteVendorById " + vendorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    @Operation(summary = "Добавление продавец", description = "Позволяет добавить нового продавецу")
    public ResponseEntity<Vendor> saveVendor(@Valid @RequestBody VendorDto vendorDto) {
        try {
            log.info("save vendor" + vendorDto);
            return new ResponseEntity<>(vendorService.saveVendor(vendorDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
}