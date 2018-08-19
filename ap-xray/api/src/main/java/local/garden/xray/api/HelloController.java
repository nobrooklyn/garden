package local.garden.xray.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/api/v1/hello")
    public String hello(String word) {
        return "hello " + word;
    }
}
