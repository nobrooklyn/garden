package local.garden.xray.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import local.garden.xray.lib.config.AbstractXrayWebConfig;

@Configuration
public class ApiWebConfig extends AbstractXrayWebConfig {
    @Bean
    public Filter tracingFilter() {
        return super.tracingFilter("web-api");
    }
}