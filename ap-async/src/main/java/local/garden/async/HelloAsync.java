package local.garden.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class HelloAsync {
    private static final Logger log = LoggerFactory.getLogger(HelloAsync.class);

    @Async
    public String hello(String uuid) {
        User u = ContextHolder.get();
        log.info("hello async: " + uuid + u);
        ContextHolder.remove();
        return "hello";
    }
}