package kg.ebooks.eBook.controller;


//import io.swagger.v3.oas.annotations.Operation;

import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
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

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    @PostMapping("/authentication")
    public ResponseEntity<?> authUser(@RequestBody SigninRequest loginRequest) {

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

//    @GetMapping
////    @Operation(summary = "Все пользователи", description = "Позволяет получить всех пользователей из базы данных")
//    public ResponseEntity<List<Client>> getAllUsers() {
//        try {
//            return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    //  @PostMapping("/register")
//    @Operation(summary = "Добавление пользователя", description = "Позволяет добавить нового пользователя")
//    public ResponseEntity<?> saveClient(@RequestBody SignupRequest signupRequest) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
//                path("/api/register").toUriString());
//        AuthenticationInfo authenticationInfo
//                = new AuthenticationInfo(
//                signupRequest.getEmail(),
//                authenticationInfo.getPassword()


//        @PostMapping("")
//        public ResponseEntity<?> saveUser(@RequestBody SignupRequest signupRequest) {
//            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
//                    path("/api/admin").toUriString());
//
//            User user = new User(
//                    signupRequest.getEmail(),
//                    passwordEncoder.encode(signupRequest.getPassword()));
//
//            List<Role> roleList = new ArrayList<>();
//            for (String role : signupRequest.getRoles()) {
//                roleList.add(roleService.   getByRoleName(role));
//            }
//            user.setRole(roleList);
//            return ResponseEntity.created(uri).body(userService.saveUser(user));
//        }
}

