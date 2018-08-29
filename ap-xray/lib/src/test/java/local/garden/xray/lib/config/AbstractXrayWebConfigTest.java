package local.garden.xray.lib.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.servlet.Filter;

import org.junit.Test;

public class AbstractXrayWebConfigTest extends AbstractXrayWebConfig {
    @Test
    public void test_config() {
        Filter filter = super.tracingFilter("test");
        assertThat(filter, is(not(nullValue())));
    }
}