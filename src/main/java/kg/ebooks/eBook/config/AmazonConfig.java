package kg.ebooks.eBook.config;

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
//            "AKIA2ODYHKQAFCC4W7ZK",
//            "7LKvgCVVndL1lJAusftw+TMbCxN4qmSDSjhFFupx"
            "AKIAZZPYDOROTII6AZMX",
            "doXbU3YapsOPhiPikUV18FyHjH0j2KI3pxHWAgnc");

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.EU_CENTRAL_1)
                .withRegion(Regions.AP_NORTHEAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
