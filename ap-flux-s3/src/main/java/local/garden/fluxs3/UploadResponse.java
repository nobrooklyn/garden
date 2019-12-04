package local.garden.fluxs3;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Value;

@Value
@JsonDeserialize
public class UploadResponse {
    private String path;
    private String version;
}