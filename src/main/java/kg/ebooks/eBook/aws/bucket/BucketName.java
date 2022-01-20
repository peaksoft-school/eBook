package kg.ebooks.eBook.aws.bucket;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 15/1/22
 * Saturday 18:12
 */
public enum BucketName {

    AWS_BUCKET_NAME("ebook-test1"),
    STORE_AUDIO_FILES("ebook-audio-test");

    private String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
