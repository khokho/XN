package ge.exen.controllers;

import ge.exen.services.Zipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class getAllFiles {
    @Autowired
    Zipper zipper;
    @GetMapping(value = "/lecturer/getAll/{exam_id}")
    public String get(@PathVariable Integer exam_id, HttpServletResponse resp){
        try {
            String path = zipper.doZip(exam_id);
            resp.sendRedirect("/" + path.substring(path.indexOf("resources")));
        } catch (IOException e) {
            e.printStackTrace();

        }

        return "template";
    }
}
