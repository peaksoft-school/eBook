package kg.ebooks.eBook.aws.bucket;

import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.aws.bucket.BucketName.AWS_BOOKS;
import static kg.ebooks.eBook.aws.bucket.BucketName.AWS_FRAGMENTS;
import static org.apache.http.entity.ContentType.*;


/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 16/1/22
 * Sunday 03:28
 */
@AllArgsConstructor
public enum FolderName {

    IMAGES(
            String.format("%s/%s", AWS_BOOKS.getBucketName(), "IMAGES"),
            new HashSet<>(Arrays.asList(IMAGE_PNG, IMAGE_JPEG, IMAGE_SVG, IMAGE_GIF))
    ),

    AUDIO_FILES(
            String.format("%s/%s", AWS_BOOKS.getBucketName(), "AUDIO_FILES"),
            new HashSet<>(Arrays.asList(
                    create("audio/basic"),
                    create("audio/mpeg"),
                    create("audio/mp3"),
                    create("audio/m4a"),
                    create("audio/vnd.wav")))
    ),
    PDF_FILES(
            String.format("%s/%s", AWS_BOOKS.getBucketName(), "PDF_FILES"),
            new HashSet<>(Arrays.asList(
                    ContentType.create("application/pdf")
            ))
    ),

    AUDIO_FRAGMENTS_FILES(
            String.format("%s/%s", AWS_FRAGMENTS.getBucketName(), "AUDIO_FRAGMENTS"),
            new HashSet<>(Arrays.asList( create("audio/basic"),
                    create("audio/mpeg"),
                    create("audio/mp3"),
                    create("audio/m4a"),
                    create("audio/vnd.wav")))
    );

    private String path;
    private Set<ContentType> contentTypes;

    public String getPath() {
        return path;
    }

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public Set<String> getMimeTypes(Set<ContentType> contentTypes) {
        return contentTypes.stream().map(ContentType::getMimeType)
                .collect(Collectors.toSet());
    }
}
