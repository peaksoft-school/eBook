package kg.ebooks.eBook.aws.bucket;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 15/1/22
 * Saturday 18:12
 */
public enum BucketName {

    AWS_BOOKS("ebook-books-bucket"),
    AWS_FRAGMENTS("ebook-fragments-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
