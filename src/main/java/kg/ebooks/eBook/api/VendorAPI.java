package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestVndr;
import kg.ebooks.eBook.db.domain.dto.security.SignupResponseVndr;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDto;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResponse;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorDtoResquest;
import kg.ebooks.eBook.db.domain.dto.vendor.VendorUpdateDto;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
@Tag(name = "Контроллер для управления продавцов")
@CrossOrigin
@RequiredArgsConstructor
public class VendorAPI {

    private final VendorService vendorService;

    @PostMapping("/signup/vendor")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию продавцу")
    public SignupResponseVndr registerVendor(@RequestBody SignupRequestVndr signupRequest) {
        return vendorService.registerVendor(signupRequest);
    }

    @GetMapping("/show/info")
    public VendorDto showVendorInfo(Authentication authentication) {
        return vendorService.showInfo(authentication.getName());
    }

    @GetMapping("/getAll")
    @Operation(summary = "Все продавцы", description = "Позволяет получить всех продавцов из базы данных самого себя")
    public List<VendorDtoResponse> getAll() {
        return vendorService.getAllVendors();
    }

    @GetMapping({"/getById/{id}"})
    @Operation(summary = "получить продавца", description = "Позволяет получить продавца по 'id'")
    public VendorDtoResquest getVendor(@PathVariable Long id) {
        return vendorService.getByIdVendor(id);
    }

    @PutMapping({"/update"})
    @Operation(summary = "Обновление продавца", description = "Позволяет обновить свой профиль продавца")
    public VendorDto updateVendorProfile(Authentication authentication,
                                        @RequestBody VendorUpdateDto vendorDto) {
        return vendorService.updateVendor(authentication.getName(), vendorDto);
    }

    @DeleteMapping({"/deleteById/{vendorId}"})
    @Operation(summary = "Удаление продавца", description = "Позволяет удалить продавца по 'id' самого себя ")
    public ResponseEntity<Void> deleteVendorById(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.ok().build();
    }
}