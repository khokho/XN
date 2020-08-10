package ge.exen.controllers;

import ge.exen.DAO.MessageSQLDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class index {
    @Autowired
    private MessageSQLDAO messageSQLDAO;

    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/login");
    }
}
