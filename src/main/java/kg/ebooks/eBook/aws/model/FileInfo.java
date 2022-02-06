package kg.ebooks.eBook.aws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.ebooks.eBook.aws.bucket.FolderName;
import lombok.*;

import javax.persistence.*;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 03:00
 */
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FolderName folderName;

    private String fileName;

    @JsonIgnore
    private boolean free;

    public void makeIsNotFree() {
        this.free = false;
    }
}
