package ge.exen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class chat {

    private void initializeSocket(){}

    @GetMapping("/chat")
    public String displayChat(HttpSession session){
        initializeSocket();
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("content", "chatContent.jsp");
        session.setAttribute("title", "ჩათი");
        session.setAttribute("chat_id", 111);
        return "template";
    }


}
