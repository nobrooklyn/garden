package local.example.train.greeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("greeting")
@Slf4j
public class GreetingController {
    @Autowired
    private GreetingRepository repository;

    @GetMapping("/")
    public String list(Model model) {
        List<Greeting> greetings = repository.findAll();
        log.info("resutl count = {}", greetings.size());
        model.addAttribute("greetings", greetings);
        return "greeting/list";
    }

    @GetMapping("/{id}")
    public String message(Model model, @PathVariable("id") String id) {
        log.info("greeting request id {}", id);
        Greeting greeting = repository.findById(id).get();
        log.info("greeting = {}", greeting);
        model.addAttribute("message", greeting.getMessage());
        return "greeting/show";
    }
}