package local.garden.fluxs3;

import java.util.UUID;

import org.springframework.http.codec.multipart.FilePart;

public class FileInfoBuilder {
    private final FilePart part;

    private FileInfoBuilder(FilePart part) {
        this.part = part;
    }

    public static FileInfoBuilder create(FilePart part) {
        return new FileInfoBuilder(part);
    }

    public FileInfo build() {
        return new FileInfo(fid(), part.filename());
    }

    private String fid() {
        return UUID.randomUUID().toString();
    }
}