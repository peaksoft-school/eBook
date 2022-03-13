package kg.ebooks.eBook.api;

import kg.ebooks.eBook.db.domain.dto.admin.BookResponseDTOFromAdmin;
import kg.ebooks.eBook.db.domain.dto.admin.RefuseToBookRequest;
import kg.ebooks.eBook.db.domain.dto.book.BookResponse;
import kg.ebooks.eBook.db.service.AdminService;
import kg.ebooks.eBook.db.service.BookGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Beksultan Mamatkadyr uulu
 * 7/2/22
 * Monday 00:32
 * hello world
 */
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin
public class AdminAPI {


    private final BookGetService bookGetService;
    private final AdminService adminService;

    @GetMapping("/get/books/requests")
    public List<BookResponseDTOFromAdmin> getAllBookStorageRequests() {
        return bookGetService.getAllBooksStorageRequests();
    }

    @GetMapping("/get/books/accepted")
    public List<BookResponseDTOFromAdmin> getAllAcceptedBooks(@RequestParam(required = false) String filterBy) {
        return bookGetService.getAllAcceptedBooks(filterBy);
    }

    @PostMapping("/accept/book/request/{bookId}")
    public String acceptBookRequest(@PathVariable Long bookId) {
        return adminService.acceptBookRequest(bookId);
    }

    @PostMapping("/refuse/book/request")
    public String refuseBookRequest(@RequestBody RefuseToBookRequest refuseToBookRequest) {
        return adminService.refuseToBookRequest(refuseToBookRequest);
    }
}
