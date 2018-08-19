package local.garden.xray.ui;

import com.amazonaws.xray.spring.aop.XRayEnabled;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@XRayEnabled
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "hello");
        return "index";
    }
}