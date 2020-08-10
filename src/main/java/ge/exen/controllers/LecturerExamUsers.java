package ge.exen.controllers;

import ge.exen.DAO.*;
import ge.exen.models.StudentExam;
import ge.exen.models.Upload;
import ge.exen.models.User;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LecturerExamUsers {
    @Autowired
    StudentExamDAO dao;

    @Autowired
    ExamDao exams;

    @Autowired
    UserDAO users;

    @Autowired
    UserUploadDAO uploadDAO;

    @GetMapping(value = "lecturer/students/{exam_id}")
    public String display(@PathVariable Integer exam_id, HttpServletRequest req, Model model){
        List<StudentExam> ls = dao.getByExam(exam_id);
        String name = exams.get(exam_id).getFullName();

        List<User> studentNames = new ArrayList<>();
        List<Upload> uploads = new ArrayList<>();
        for(StudentExam entry : ls){
            studentNames.add(users.getUser(entry.getStudentId()));

            List<Upload> curr = uploadDAO.getForUser(entry);
            if(curr.size() == 0) uploads.add(null); else
            uploads.add(curr.get(curr.size()-1));
        }

        req.setAttribute("names", studentNames);
        req.setAttribute("examID", exam_id);
        req.setAttribute("uploads", uploads);
        model.addAttribute("content", "lecturerExamUsers.jsp");
        model.addAttribute("title", name);
        return "template";
    }
}
