package kg.ebooks.eBook.db.domain.dto;

import lombok.Data;

/**
 * @author Beksultan
 */
@Data
public class Response {
    private String value;

    public Response(String value) {
        this.value = value;
    }
}
