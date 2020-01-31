package local.example.train.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("greeting")
@Slf4j
public class GreetingController {
    @Autowired
    private GreetingRepository repository;

    @GetMapping("/hello")
    public String message(Model model) {
        Greeting greeting = repository.findById("G3").get();
        log.info("greeting = {}", greeting);
        model.addAttribute("message", greeting.getMessage());
        return "greeting/index";
    }
}