package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    @Autowired
    private final ClientService clientService;

    private final SignupRequestClntMapper clientMapper;

    @PostMapping("/signup/client")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию покупателю")
    public SignupRequestClnt registerClient(@RequestBody SignupRequestClnt signupRequest) {
        return clientService.registerClient(signupRequest);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDto>> getAllClients(Client client) {
        try {
            log.info("ClientController  - getClients -: {}", client);
            return new ResponseEntity<>(clientService.getClients(client), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @GetMapping({"/getById/{id}"})
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id) {
        try {
            log.info("ClientController  - getClient - id: {}", id);
            return new ResponseEntity<>(clientMapper.clientGetById(clientService.getClientById(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_GATEWAY);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ClientDto> saveClient(@Valid @RequestBody ClientDto clientDto) {
        try {
            log.info("create clients + {} " + clientDto);
            return new ResponseEntity<>(clientService.saveClient(clientDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping({"/update/{clientId}"})
    public ResponseEntity<ClientDto> updateClient(@PathVariable("clientId") Long id,
                                                  @RequestBody ClientDto client) {
        log.info("Updating client: {}", client);
        try {
            return new ResponseEntity<>(clientService.updateClient(id, client), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @DeleteMapping({"/delete/{clientId}"})
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long id) {
        log.info("Deleting client with id: {}, id" + id);
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }
}


