package kg.ebooks.eBook.aws.repository;

import kg.ebooks.eBook.aws.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 03:04
 */
@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {
}
