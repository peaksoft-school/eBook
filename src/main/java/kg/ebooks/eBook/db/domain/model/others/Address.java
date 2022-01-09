package kg.ebooks.eBook.db.domain.model.others;

import kg.ebooks.eBook.db.domain.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 21:27
 */
@Entity
@Table(name = "addresses")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;

    @NotNull(message = "you have to define country to this address")
    private Country country;

    @NotBlank(message = "you have to define city to this address")
    private String cityName;

    @NotBlank(message = "you have to define full address (street/house)")
    private String address;

    @NotBlank(message = "you have to define post code for this address")
    private String postCode;

}
