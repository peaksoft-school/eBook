package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.book.SearchDto;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
public class SearchApi {

    private final SearchService searchService;
    private final BookRepository repository;

    @GetMapping("/books")
    List<SearchDto> getBooks(@RequestParam Optional<String> search) {
        return searchService.findAll(search.get());
    }
}