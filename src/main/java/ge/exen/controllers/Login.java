package ge.exen.controllers;

import ge.exen.dto.UserLoginDTO;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class Login {
    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String render(HttpSession session){
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("content", "login.jsp");
        session.setAttribute("title", "შესვლა");
        return "/template";
    }

    @PostMapping(value = "/login")
    public String login(UserLoginDTO dto,
                        HttpServletRequest req,
                        HttpSession session){
        if(!userService.login(dto)){
            req.setAttribute("bad_attempt", "true");
            session.setAttribute("sidebar", "adminSidebar.jsp");
            session.setAttribute("content", "login.jsp");
            session.setAttribute("title", "შესვლა");
            return "/template";
        }
        return "/template";
    }
}
