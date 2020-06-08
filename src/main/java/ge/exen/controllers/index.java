package ge.exen.controllers;

import ge.exen.DAO.MessageSQLDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {


    @Autowired
    private MessageSQLDAO messageSQLDAO;

    @GetMapping("/")
    public String home() {
        System.out.println("hello from the other sidee");
        //messageSQLDAO.get(0); test sql
        return "index";
    }

}
