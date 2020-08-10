package ge.exen.controllers;

import ge.exen.services.Zipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class getAllFiles {
    @Autowired
    Zipper zipper;
    @GetMapping(value = "/lecturer/getAll/{exam_id}")
    public RedirectView get(@PathVariable Integer exam_id, HttpServletResponse resp){
        String path = null;
        try {
            path = zipper.doZip(exam_id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new RedirectView("/" + path.substring(path.indexOf("resources")));
    }
}
