package ge.exen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDenied {

    @GetMapping(value = "/accessDenied")
    public String deny(){
        return "insuffPriv";
    }
}
