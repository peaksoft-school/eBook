package kg.ebooks.eBook.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/")
public class WelcomeTestApi {

    @GetMapping
    public String welcome() {
        return "Welcome to eBook application!";
    }
}
