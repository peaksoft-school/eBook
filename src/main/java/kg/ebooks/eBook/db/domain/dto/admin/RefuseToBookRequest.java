package kg.ebooks.eBook.db.domain.dto.admin;

import lombok.Getter;
import lombok.Setter;

/**
 * created by Beksultan Mamatkadyr uulu
 * 7/2/22
 * Monday 00:45
 * hello world
 */
@Getter @Setter
public class RefuseToBookRequest {
    private Long bookId;
    private String reasonForRejection;
}
