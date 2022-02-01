package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtTestAPI {

    @Operation(summary = "Token Test Request", description = "Testing token")
    @GetMapping("/test")
    public String test() {
        return "your token is working";
    }
}