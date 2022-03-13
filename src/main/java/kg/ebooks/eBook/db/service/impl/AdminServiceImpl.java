package kg.ebooks.eBook.db.service.impl;

import com.google.gson.JsonObject;
import kg.ebooks.eBook.db.domain.dto.admin.RefuseToBookRequest;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.domain.model.users.Vendor;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.AdminService;
import kg.ebooks.eBook.db.service.EmailService;
import kg.ebooks.eBook.db.service.VendorService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Map;

import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;

/**
 * created by Beksultan Mamatkadyr uulu
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

    private final BookRepository bookRepository;
    private final VendorService vendorService;
    private final EmailService emailService;

    @Override
    public String acceptBookRequest(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        String.format("book with id = %d does not exists in database", bookId)
                ));
        book.setRequestStatus(ACCEPTED);
        return response("ACCEPTED");
    }

    @Override
    public String refuseToBookRequest(RefuseToBookRequest refuseToBookRequest) {
        Book book = bookRepository.findById(refuseToBookRequest.getBookId())
                .orElseThrow(() -> new DoesNotExistsException(
                        String.format("book with id = %d does not exists in database", refuseToBookRequest.getBookId())
                ));
        RequestStatus requestStatus = DENIED;
        requestStatus.setReason(refuseToBookRequest.getReasonForRejection());
        book.setRequestStatus(requestStatus);

        Vendor vendor = vendorService.findByBookId(book);

        String message = String.format("Dear %s <br> Your book rejected from Admin <br> There reason: %s", vendor.getFirstName(),
                refuseToBookRequest.getReasonForRejection());

        emailService.send(vendor.getEmail(), message);

        return response("DENIED");
    }

    private String response(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("RESPONSE", message);
        return jsonObject.getAsString();
    }
}
