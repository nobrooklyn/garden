package local.garden.async;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    private static final Logger log = LoggerFactory.getLogger(Greeting.class);
    @Autowired
    private Hello hello;
    @Autowired
    private HelloAsync helloAsync;

    @GetMapping("/greeting")
    public String greeting() {
        String word = hello.hello();
        return word;
    }

    @GetMapping("/async/{id}")
    public String async(@PathVariable("id") Integer id) throws InterruptedException {
        User u = new User(id, "tarou");
        ContextHolder.set(u);
        TimeUnit.SECONDS.sleep(5);
        String uuid = UUID.randomUUID().toString();
        log.info(uuid + " " + u);
        helloAsync.hello(uuid);
        ContextHolder.remove();
        return "async";
    }
}