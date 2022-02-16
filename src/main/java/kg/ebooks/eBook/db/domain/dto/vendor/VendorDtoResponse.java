package kg.ebooks.eBook.db.domain.dto.vendor;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VendorDtoResponse {
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
