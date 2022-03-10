package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.domain.dto.book.BookResponseDTOSort;
import kg.ebooks.eBook.db.domain.dto.sort.SortRequest;
import kg.ebooks.eBook.db.service.SortService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 15:39
 * hello world
 */
@RestController
@RequestMapping("api/books")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "This API to sort books")
@Slf4j
public class SortAPI {

    private final SortService service;

    @GetMapping("/sort")
    @Operation(summary = "sort", description = "This method to sort books with genres & price & languages and also book type" +
            " and I have one problem I can fix it but if you're giving genre id that doesn't exists it will work," +
            " but it thinks that it's nothing, It helps code to work fastly")
    public Set<BookResponseDTOSort> sortList(@RequestParam(required = false) String filterBy) {
        log.info("filterBy = {}", filterBy);
        return service.sort(filterBy);
    }

    @Operation(summary = "sort by type", description = " This method for sort books by type, " +
            "you should give type of books like that <br/>" +
            "'paperBook' -> OK <br/>" +
            "'electronicBook' -> OK <br/>" +
            "'audioBook' -> OK <br/>" +
            "'baibolot' -> BADREQUEST 400 ")
    @GetMapping("/sort/by/type")
    public List<BookResponse> sortBooksByType(@RequestParam String type) {
        return service.findAllByType(type);
    }
}
