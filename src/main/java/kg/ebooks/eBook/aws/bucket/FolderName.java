package kg.ebooks.eBook.aws.bucket;

import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;

import java.util.Set;
import java.util.stream.Collectors;

import static kg.ebooks.eBook.aws.bucket.BucketName.AWS_BUCKET_NAME;
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
            String.format("%s/%s", AWS_BUCKET_NAME.getBucketName(), "IMAGES"),
            Set.of(IMAGE_PNG, IMAGE_JPEG, IMAGE_SVG, IMAGE_GIF)
    ),

    AUDIO_FILES(
            String.format("%s/%s", AWS_BUCKET_NAME.getBucketName(), "AUDIO_FILES"),
            Set.of( create("audio/basic"),
                    create("audio/mpeg"),
                    create("audio/mp4"),
                    create("audio/mp3"),
                    create("audio/vnd.wav"))
    ),
    PDF_FILES(
            String.format("%s/%s", AWS_BUCKET_NAME.getBucketName(), "PDF_FILES"),
            Set.of(
                    ContentType.create("application/pdf")
            )
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
