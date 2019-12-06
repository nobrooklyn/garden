package local.garden.fluxs3;

import java.nio.file.Path;
import java.nio.file.Paths;

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

@Component
@Slf4j
public class UploadHandler {
    private final S3AsyncClient s3;
    private final FileRepository repo;
    private static final String bucket = "ap-cdk-bucket-1";

    @Autowired
    public UploadHandler(FileRepository repo) {
        this.s3 = S3AsyncClient.builder().region(Region.AP_NORTHEAST_1).build();
        this.repo = repo;
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
        final FileInfo info = FileInfoBuilder.create(filePart).build();
        final PutObjectRequest req = PutObjectRequest.builder().bucket(bucket).key(info.getFid()).build();

        Path localPath = Paths.get(System.getProperty("java.io.tmpdir"), filePart.filename());
        filePart.transferTo(localPath);

        final AsyncRequestBody body = AsyncRequestBody.fromFile(localPath);

        return Mono.fromFuture(s3.putObject(req, body)).doOnError(e -> log.error(e.getMessage(), e))
                .map(res -> new UploadResponse(req.key(), res.versionId())).doOnSuccess(res -> repo.save(info));
    }

}