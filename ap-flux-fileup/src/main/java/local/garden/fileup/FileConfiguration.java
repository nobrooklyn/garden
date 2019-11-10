package local.garden.fileup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConfiguration {
    final static String downloadPath = "/download";

    @Value("${file.download.url:http://localhost:8080}")
    private String downloadUrl;

    String getDownloadEndpoint() {
        return downloadUrl + downloadPath;
    }
}