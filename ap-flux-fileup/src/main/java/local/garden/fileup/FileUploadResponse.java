package local.garden.fileup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.DigestUtils;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j
public class FileUploadResponse {
    private String fileName;
    private String downloadUrl;
    private String contetnType;
    private long size;
    private String md5;

    FileUploadResponse(File file, String downloadEndpoint) {
        this.fileName = file.getName();
        this.downloadUrl = downloadEndpoint + "/" + fileName;
        try {
            this.contetnType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            log.info("not set contentType", e);
            this.contetnType = "no data";
        }
        this.size = file.length();
        try {
            this.md5 = DigestUtils.md5DigestAsHex(new FileInputStream(file));
        } catch (IOException e) {
            log.info("not set md5", e);
            this.md5 = "no data";
        }
    }
}
