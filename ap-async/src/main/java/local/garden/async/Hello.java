package local.garden.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Hello {
    private static final Logger log = LoggerFactory.getLogger(Hello.class);
    public String hello() {
        log.info("hello");
        return "hello";
    }
}