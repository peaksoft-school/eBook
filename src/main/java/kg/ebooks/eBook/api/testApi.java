package kg.ebooks.eBook.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/test")
public class testApi {

    @GetMapping
    public String showHello() {
        return "Hello";
    }
}
