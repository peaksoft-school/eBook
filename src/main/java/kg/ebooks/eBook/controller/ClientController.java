package kg.ebooks.eBook.controller;


//import io.swagger.v3.oas.annotations.Operation;

import com.amazonaws.services.pinpoint.model.MessageResponse;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.dto.JwtResponse;
import kg.ebooks.eBook.db.dto.SigninRequest;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.jwt.JwtUtils;
import kg.ebooks.eBook.security.password.encoder.PasswordEncode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @PostMapping("/authentication")
    public ResponseEntity<?> authClient(@RequestBody SigninRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                authenticationInfo.getEmail(),
                authenticationInfo.getAuthority()));
    }

//    @PostMapping("/register")
////    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
//    public ResponseEntity<?> saveClient(@RequestBody Client client) {
//        try {
//            clientService.saveClient(client);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping
////    @Operation(summary = "Все пользователи", description = "Позволяет получить всех пользователей из базы данных")
//    public ResponseEntity<List<Client>> getAllUsers() {
//        try {
//            return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}




