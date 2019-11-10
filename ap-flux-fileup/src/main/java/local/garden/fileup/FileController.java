package local.garden.fileup;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class FileController {
    private final FileConfiguration conf;

    @Autowired
    public FileController(FileConfiguration conf) {
        this.conf = conf;
    }

    @ResponseBody
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FileUploadResponse> upload(@RequestPart("f") Flux<FilePart> parts) {
        return parts.flatMap(part -> save(part));
    }

    private Mono<FileUploadResponse> save(FilePart part) {
        String fileName = part.filename();
        File file = new File("tmp/" + fileName);
        log.info("save to {}", file.getAbsolutePath());
        return part.transferTo(file).thenReturn(new FileUploadResponse(file, conf.getDownloadEndpoint()));
    }
}