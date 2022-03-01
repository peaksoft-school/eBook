package kg.ebooks.eBook.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.ebooks.eBook.db.domain.dto.book.SearchDto;
import kg.ebooks.eBook.db.service.SearchService;
import kg.ebooks.eBook.exceptions.EmptyListException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "This API to search bar")
public class SearchAPI {

    private final SearchService searchService;

    @Operation(summary = "search by books & genres & authors & publishing house")
    @GetMapping("/books")
    List<SearchDto> getBooks(@RequestParam String search) {
        List<SearchDto> all = searchService.findAll(search);
        if (all.size() < 1) {
            throw new EmptyListException("No results found for your search \"" + search + "\"");
        }
        return all;
    }

    @ExceptionHandler(EmptyListException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String emptyListExceptionHandler(EmptyListException e) {
        return e.getMessage();
    }
}