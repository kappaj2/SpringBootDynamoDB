package com.ktk.dynamo.dynamotest.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableDynamoDBRepositories(basePackages = "com.ktk.dynamo.dynamotest.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    /**
     * Create a bean with all the properties configured to enable DynamoDB access.
     * Access keys and endpoint is already configured as well.
     * @param awsStaticCredentialsProvider
     * @return
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSStaticCredentialsProvider awsStaticCredentialsProvider) {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, ""))
                .build();
    }

    /**
     * Create a bean top provide the AWSStaticCredentialsProvider using the AWSCredentials bean injected by Spring.
     * @param awsCredentials
     * @return
     */
    @Bean
    public AWSStaticCredentialsProvider awsStaticCredentialsProvider(AWSCredentials awsCredentials) {
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    /**
     * Create a bean to provide the AWSCredentials configured in the applicatiomn.properties file.
     * @return
     */
    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
