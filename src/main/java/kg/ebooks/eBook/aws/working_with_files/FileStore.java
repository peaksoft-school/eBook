package kg.ebooks.eBook.aws.working_with_files;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 02:55
 */
@Service
public interface FileStore {

    void save(String path,
              String fileName,
              Optional<Map<String, String>> optionalMetadata,
              InputStream inputStream
    );

    byte[] download(String path, String key);

    void delete(String filePath, String fileName);
}
