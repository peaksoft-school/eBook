package kg.ebooks.eBook.aws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.ebooks.eBook.aws.bucket.FolderName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private FolderName folderName;

    private String fileName;

    @JsonIgnore
    private boolean free;

    public void makeIsNotFree() {
        this.free = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileInfo fileInfo = (FileInfo) o;
        return id != null && Objects.equals(id, fileInfo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
