package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResquestResponse;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/client")
@Tag(name = "Контроллер для управления пользователем")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ClientAPI {

    private final ClientService clientService;
    private final SignupRequestClntMapper clientMapper;

    @PostMapping("/signup/client")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию покупателю")
    public SignupRequestClnt registerClient(@RequestBody SignupRequestClnt signupRequest) {
        return clientService.registerClient(signupRequest);
    }


    @GetMapping("/getAll")
    @Operation(summary = "Все пользователи", description = "Позволяет получить всех пользователей из базы данных")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        try {
            log.info("ClientController  - getClients -: {}");
            return new ResponseEntity<>(clientService.getClients(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @GetMapping({"/getById/{id}"})
    @Operation(summary = "Пользователь(id)", description = "Позволяет получить пользователя по 'id'")
    public ResponseEntity<ClientDtoResquestResponse> getClient(@PathVariable("id") Long id) {
        try {
            log.info("ClientController  - getClient - id: {}", id);
            return new ResponseEntity<>(clientMapper.clientGetById(clientService.getClientById(id)), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientDto clientDto) {
        try {
            log.info("create clients + {} " + clientDto);
            return new ResponseEntity<>(clientService.saveClient(clientDto), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @PutMapping({"/update/{clientId}"})
    @Operation(summary = "Обновление пользователя", description = "Позволяет обновить пользователя")
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
    @Operation(summary = "Удаление пользователя", description = "Позволяет удалить пользователя")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long id) {
        log.info("Deleting client with id: {}, id" + id);
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }
}


