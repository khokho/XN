package ge.exen.controllers;

import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class register {
    @Autowired
    UserService userService;

    @GetMapping(value = "/admin/register")
    public String render(HttpSession session){
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("content", "register.jsp");
        session.setAttribute("title", "რეგისტრაცია");
        return "/template";
    }

    @PostMapping(value = "/admin/register")
    public String login(UserRegisterDTO dto,
                        HttpServletRequest req,
                        HttpServletResponse resp){
        if(!userService.registerNewUser(dto)){
            req.setAttribute("bad_attempt", "true");
            req.setAttribute("sidebar", "adminSidebar.jsp");
            req.setAttribute("content", "register.jsp");
            req.setAttribute("title", "შესვლა");
            return "/template";
        }
        return "/template";
    }
}
