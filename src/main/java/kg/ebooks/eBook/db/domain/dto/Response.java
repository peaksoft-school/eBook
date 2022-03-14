package kg.ebooks.eBook.db.domain.dto;

import lombok.Data;

/**
 * @author Beksultan
 */
@Data
public class Response {
    private String result;

    public Response(String result) {
        this.result = result;
    }
}
