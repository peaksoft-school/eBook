package kg.ebooks.eBook.controller;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
//    @Operation(summary = "Все пользователи", description = "Позволяет получить всех пользователей из базы данных")
    public ResponseEntity<List<Vendor>> getAllUsers() {
        try {
            return new ResponseEntity<>(vendorService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
//    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
    public ResponseEntity<?> saveClient(@RequestBody Vendor vendor) {
        try {
            vendorService.saveVendor(vendor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
