package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.dto.admin.RefuseToBookRequest;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.RequestStatus;
import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.AdminService;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kg.ebooks.eBook.db.domain.model.enums.RequestStatus.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * 7/2/22
 * Monday 00:36
 * hello world
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final BookRepository bookRepository;

    @Override
    public String acceptBookRequest(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new DoesNotExistsException(
                        String.format("book with id = %d does not exists in database", bookId)
                ));
        book.setRequestStatus(ACCEPTED);
        return "ACCEPTED";
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
        return "DENIED";
    }
}
