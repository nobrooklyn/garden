package local.garden.fluxs3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component
@Slf4j
public class UploadHandler {
    private final S3AsyncClient s3;
    private static final String bucket = "ap-cdk-bucket-1";
    private static final String key = "app/test";

    @Autowired
    public UploadHandler() {
        this.s3 = S3AsyncClient.builder().region(Region.AP_NORTHEAST_1).build();
    }

    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route().path("/upload", u -> {
            u.POST("/", this::upload);
        }).build();
    }

    public Mono<ServerResponse> upload(final ServerRequest req) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(req.multipartData().map(d -> d.get("f")).flatMapMany(Flux::fromIterable).cast(FilePart.class)
                        .flatMap(f -> putObject(f)).log(), UploadResponse.class);
    }

    private Mono<UploadResponse> putObject(final FilePart filePart) {
        final PutObjectRequest req = PutObjectRequest.builder().bucket(bucket).key(key + "/" + filePart.filename())
                .build();

        Path localPath = Paths.get(System.getProperty("java.io.tmpdir"), filePart.filename());
        filePart.transferTo(localPath);

        final AsyncRequestBody body = AsyncRequestBody.fromFile(localPath);

        return Mono.fromFuture(s3.putObject(req, body)).doOnError(e -> log.error(e.getMessage(), e))
                .map(res -> new UploadResponse(req.bucket() + "/" + req.key(), res.versionId()));
    }

// -- for debug
//    private CompletableFuture<PutObjectResponse> putObject2(final FilePart filePart) {
//        CompletableFuture<PutObjectResponse> future = CompletableFuture.supplyAsync(() -> {
//            log.info(filePart.filename() + " start.");
//            PutObjectResponse res = PutObjectResponse.builder().versionId("1").build();
//            try {
//                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(filePart.filename() + " done.");
//            return res;
//        });
//
//        return future;
//    }
}