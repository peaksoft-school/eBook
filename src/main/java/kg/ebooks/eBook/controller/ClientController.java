package kg.ebooks.eBook.controller;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController("/api/v2/client")
@RequestMapping("")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            return new ResponseEntity<>(clientService.getClients(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @GetMapping({"/getById/{id}"})
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(clientService.getClientById(id), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        try {
            return new ResponseEntity<>(clientService.saveClient(client), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(CREATED);

        }
    }

    @PutMapping({"/update/{clientId}"})
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long id, @RequestBody Client client) {
        try {
            return new ResponseEntity<>(clientService.updateClient(id, client), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @DeleteMapping({"/delete/{clientId}"})
    public ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long id) {
        try {
            return new ResponseEntity<>(clientService.getClientById(id), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

}
