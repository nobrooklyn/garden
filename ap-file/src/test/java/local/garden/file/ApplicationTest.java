package local.garden.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.model.S3Object;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ApplicationTest {
    @Rule
    public TemporaryFolder tmpDir = new TemporaryFolder();

    /**
     * initialize for localstack
     */
    @BeforeClass
    public static void init() {
        System.setProperty(Constants.ACCESS_KEY_ID_KEY, "dummy");
        System.setProperty(Constants.SECRET_KEY_KEY, "dummy");
        System.setProperty(Constants.END_POINT_URL_KEY, "http://localhost:4572");
        System.setProperty(Constants.PATH_STYLE_ACCESS_KEY, "true");
        System.setProperty(Constants.CHUNKED_ENCODING_KEY, "false");
        System.setProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation", "true");
    }

    @Test
    public void test_s3() throws IOException {
        File file = tmpDir.newFile("test.txt");

        Application app = new Application();
        app.create("test-bucket");
        app.put("test-bucket", "test.txt", file);
        S3Object obj = app.get("test-bucket", "test.txt");

        assertThat(obj.getBucketName(), is("test-bucket"));
    }
}