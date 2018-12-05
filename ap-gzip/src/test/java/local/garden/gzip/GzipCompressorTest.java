package local.garden.gzip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.jupiter.api.Test;

public class GzipCompressorTest {
    @Test
    public void test_gzip() {
        String word = "hello world";
        byte[] gz = gzip(word);
        System.out.println(Arrays.toString(gz));
        String ungz = gunzip(gz);

        assertEquals(word, ungz);
    }

    private byte[] gzip(String text) {
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        try (GZIPOutputStream gzOut = new GZIPOutputStream(baOut)) {
            gzOut.write(text.getBytes());
            gzOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        byte[] comp = baOut.toByteArray();

        return comp;
    }

    private String gunzip(byte[] gz) {
        try (ByteArrayInputStream baIn = new ByteArrayInputStream(gz);
                GZIPInputStream gzIn = new GZIPInputStream(baIn)) {
            byte[] data = gzIn.readAllBytes();
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}