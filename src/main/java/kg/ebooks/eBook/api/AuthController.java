package kg.ebooks.eBook.api;


import io.swagger.v3.oas.annotations.Operation;

import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.jwt.JwtUtils;
import kg.ebooks.eBook.db.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;



    @PostMapping()
    @Operation(summary = "Прохождение аутентификации", description = "Позволяет пройти аутентификацию")
    public ResponseEntity<?> authApi(@RequestBody SigninRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication.getPrincipal();
        return ok(new JwtResponse(jwt,   //return ResponseEntity.ok
                authenticationInfo.getEmail(),
                authenticationInfo.getAuthority()));
    }
}




