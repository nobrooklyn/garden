package local.garden.file;

import java.io.File;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class Application {
    private final AmazonS3 s3;

    public Application() {
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

        String endpointUrl = System.getProperty(Constants.END_POINT_URL_KEY);
        if (endpointUrl != null) {
            EndpointConfiguration conf = new EndpointConfiguration(endpointUrl, Regions.AP_NORTHEAST_1.toString());
            builder.setEndpointConfiguration(conf);
        }

        if ("true".equals(System.getProperty(Constants.PATH_STYLE_ACCESS_KEY))) {
            builder.setPathStyleAccessEnabled(true);
        }

        if ("false".equals(System.getProperty(Constants.CHUNKED_ENCODING_KEY))) {
            builder.setChunkedEncodingDisabled(true);
        }

        s3 = builder.build();
    }

    public void create(String bucketName) {
        s3.createBucket(bucketName);
    }

    public S3Object get(String bucketName, String key) {
        S3Object obj = s3.getObject(new GetObjectRequest(bucketName, key));

        return obj;
    }

    public void put(String bucketName, String key, File file) {
        s3.putObject(bucketName, key, file);
    }
}
