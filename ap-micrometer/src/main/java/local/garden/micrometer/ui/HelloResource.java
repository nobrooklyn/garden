package local.garden.micrometer.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
    @GetMapping("/hello")
    public String say() {
        return "hello";
    }

    @GetMapping("/hello2")
    public String say(String w) throws Exception {
        if (w == null || "".equals(w)) {
            throw new Exception("w is blank");
        }
        return "Hello w";
    }
}