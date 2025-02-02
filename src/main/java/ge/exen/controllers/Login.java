package ge.exen.controllers;

import ge.exen.dto.UserLoginDTO;
import ge.exen.models.User;
import ge.exen.services.IUserService;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class Login {
    @Autowired
    IUserService userService;

    @GetMapping(value = "/login")
    public String render(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(userService.getCurrentUser() != null){
            switch (userService.getCurrentUser().getStatus()){
                case User.ADMIN :
                    resp.sendRedirect("/admin/register");
                    break;
                case User.LECTURER:
                    resp.sendRedirect("/lecturer/exams");
                    break;
                case User.STUDENT:
                    resp.sendRedirect("/eh");
                    break;
            }
        }
        req.setAttribute("content", "login.jsp");
        req.setAttribute("title", "შესვლა");
        return "/template";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        userService.logout();
        req.setAttribute("content", "login.jsp");
        req.setAttribute("title", "შესვლა");
        req.setAttribute("loggedin", "0");
        try {
            resp.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/template";
    }

    @PostMapping(value = "/login")
    public String login(UserLoginDTO dto,
                        HttpServletRequest req,
                        Model model,
                        HttpServletResponse resp) throws IOException {
        if(!userService.login(dto)){
            req.setAttribute("bad_attempt", "true");
            model.addAttribute("content", "login.jsp");
            model.addAttribute("title", "შესვლა");
            return "/template";
        }
        model.addAttribute("loggedin", "1");
        resp.sendRedirect("/login");
        return "/template";
    }
}
