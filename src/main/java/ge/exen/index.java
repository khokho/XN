package ge.exen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

    @GetMapping("/")
    public String home() {
        System.out.println("hello from the other sidee");
        return "index";
    }

}
