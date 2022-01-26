package kg.ebooks.eBook.controller;

import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.mapper.ClientMapper;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.service.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("")
public class ClientControllers {


    private final ClientServices clientService;

    @Autowired
    public ClientControllers(@Qualifier("clientServiceImpls") ClientServices clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDto>> getAllClients(Client client) {
        try {
            return new ResponseEntity<>(clientService.getClients(client), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @GetMapping({"/getById/{id}"})
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(ClientMapper.clientDto(clientService.getClientById(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ClientDto> saveClient(@Valid @RequestBody ClientDto clientDto) {
        clientService.saveClient(ClientMapper.makeClient(clientDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public void deleteClient(@PathVariable("clientId") Long id) {
        clientService.deleteClientById(id);
    }

}
