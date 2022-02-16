package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.aws.exceptions.InvalidFileException;

import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResponse;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResquest;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.exceptions.InvalidFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/client")
@Tag(name = "Контроллер для управления клиентом")
@CrossOrigin
@RequiredArgsConstructor
public class ClientAPI {

    private final ClientService clientService;
    private final SignupRequestClntMapper clientMapper;

    @PostMapping("/signup/client")
    @Operation(summary = "Прохождение регистрации", description = "Позволяет пройти регистрацию покупателю")
    public SignupRequestClnt registerClient(@RequestBody SignupRequestClnt signupRequest) {
        return clientService.registerClient(signupRequest);
    }


    @GetMapping("/getAll")
    @Operation(summary = "Все клиенты", description = "Позволяет получить всех клиентов из базы данных самого себя")
    public List<ClientDtoResponse> getAllClients() {
        return clientService.getClients();

    }

    @GetMapping({"/getById/{id}"})
    @Operation(summary = "получить клиента", description = "Позволяет получить клиента по 'id' ")
    public ClientDtoResquest getClient(@PathVariable("id") Long id) {
        return clientMapper.clientGetById(clientService.getClientById(id));
    }

    @PutMapping({"/update/{clientId}"})
    @Operation(summary = "Обновление клиента", description = "Позволяет обновить свой профиль клиента")
    public ClientDto updateClient(@PathVariable("clientId") Long id,
                                  @RequestBody ClientDto client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping({"/delete/{clientId}"})
    @Operation(summary = "Удаление клиента", description = "Позволяет удалить клиента самого себя")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions (
            MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}


