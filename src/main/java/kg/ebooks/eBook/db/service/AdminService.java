package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.admin.RefuseToBookRequest;

/**
 * created by Beksultan Mamatkadyr uulu
 * 7/2/22
 * Monday 00:36
 * hello world
 */
public interface AdminService {
    String acceptBookRequest(Long bookId);

    String refuseToBookRequest(RefuseToBookRequest refuseToBookRequest);
}
