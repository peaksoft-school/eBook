package kg.ebooks.eBook.aws.model;

import kg.ebooks.eBook.aws.bucket.FolderName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FolderName folderName;

    private String fileName;
}
