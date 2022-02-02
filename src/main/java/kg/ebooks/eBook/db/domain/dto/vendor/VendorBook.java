package kg.ebooks.eBook.db.domain.dto.vendor;

import kg.ebooks.eBook.aws.model.FileInfo;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class VendorBook {

    private Long bookId;
    private FileInfo image;
    private String bookName;
    private String author;
    //    private BigDecimal price;
    private BigDecimal netPrice;
    private byte discount;
    private BigDecimal discountedPrice;


}
