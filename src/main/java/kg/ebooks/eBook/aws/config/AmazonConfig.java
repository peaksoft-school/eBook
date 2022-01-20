package kg.ebooks.eBook.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : aws_s3_test2
 * 15/1/22
 * Saturday 18:11
 */
@Configuration
@RequiredArgsConstructor
public class AmazonConfig {

    public static final AWSCredentials awsCredentials = new BasicAWSCredentials(
            "AKIAZZPYDOROR5URMT24",
            "Mip7G2FRSjwughKdol+9gDNumRgUQJ/alWWbM0yc"
    );

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
