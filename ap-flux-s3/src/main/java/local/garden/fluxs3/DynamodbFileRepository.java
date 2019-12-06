package local.garden.fluxs3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Repository
@Slf4j
public class DynamodbFileRepository implements FileRepository {
    private final DynamoDbAsyncClient db;
    private final FileInfoDynamodbMapper mapper;

    private static final String TABLE = "ap-cdk-dynamodb-1";

    @Autowired
    public DynamodbFileRepository() {
        this.db = DynamoDbAsyncClient.builder().region(Region.AP_NORTHEAST_1).build();
        this.mapper = new FileInfoDynamodbMapper();
    }

    @Override
    public Mono<Void> save(FileInfo info) {
        PutItemRequest req = PutItemRequest.builder().tableName(TABLE).item(mapper.to(info)).build();
        return Mono.fromFuture(db.putItem(req)).doOnError(e -> log.error(e.getMessage(), e)).log().then();
    }
}