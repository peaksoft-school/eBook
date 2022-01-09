package kg.ebooks.eBook.db.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 8/1/22
 * Saturday 19:52
 */
@Entity
@Table(name = "images")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private byte[] content;

    private String imageName;
}
