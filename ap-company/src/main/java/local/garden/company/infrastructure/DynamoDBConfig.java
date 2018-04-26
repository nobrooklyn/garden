package local.garden.company.infrastructure;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "local.garden.company.infrastructure")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String signingRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, signingRegion))
                .withCredentials(new ProfileCredentialsProvider("dev")).build();
    }

    @Bean
    public DynamoDB dynamoDB() {
        return new DynamoDB(amazonDynamoDB());
    }
}