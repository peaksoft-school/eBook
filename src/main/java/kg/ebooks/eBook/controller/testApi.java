package kg.ebooks.eBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping
public class testApi {

    @GetMapping
    public String showHello() {
        return "Hello";
    }

}
