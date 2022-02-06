package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.service.BookGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:30
 * hello world
 */
@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookGetAPI {

    private final BookGetService bookGetService;

}
