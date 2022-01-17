package kg.ebooks.eBook.controller;


import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {

private  final ClientService clientService;


    @GetMapping
//    @Operation(summary = "Все пользователи", description = "Позволяет получить всех пользователей из базы данных")
    public ResponseEntity<List<Client>> getAllUsers() {
        try {
            return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
//    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        try {
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
