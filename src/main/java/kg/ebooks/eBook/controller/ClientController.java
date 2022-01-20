package kg.ebooks.eBook.controller;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("")
@RequestMapping("")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllTodos() {
        List<Client> clients = clientService.getClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        Client client1 = clientService.saveClient(client);
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @PutMapping({"/{clientId}"})
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long id, @RequestBody Client client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{clientId}"})
    public ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
