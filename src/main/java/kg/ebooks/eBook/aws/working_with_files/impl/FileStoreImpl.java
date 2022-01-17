package kg.ebooks.eBook.aws.working_with_files.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import kg.ebooks.eBook.aws.working_with_files.FileStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 02:59
 */
@Service
@AllArgsConstructor
@Slf4j
public class FileStoreImpl implements FileStore {

    private final AmazonS3 s3;

    @Override
    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {

        ObjectMetadata objectMetadata = new ObjectMetadata();

        optionalMetadata.ifPresent(stringStringMap -> {
            if (!stringStringMap.isEmpty()) {
                stringStringMap.forEach(objectMetadata::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            log.error("file {} cannot save to amazon s3 bucket", fileName);
            throw new IllegalStateException(
                    "failed to upload file to amazon s3 bucket"
            );
        }
    }

    @Override
    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException amazonServiceException) {
            throw new IllegalStateException(
                    String.format("failed to download file [%s] from amazon", key)
            );
        }
    }

    @Override
    public void delete(String filePath, String fileName) {
        try {
            s3.deleteObject(filePath, fileName);
        } catch (AmazonServiceException ex) {
            log.info("failed to delete file = {} from amazon s3 bucket", fileName);
            throw new IllegalStateException(
                    String.format("failed to delete file [%s] from amazon", fileName)
            );
        }
    }
}
