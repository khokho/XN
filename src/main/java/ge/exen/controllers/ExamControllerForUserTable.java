package ge.exen.controllers;

import ge.exen.DAO.StudentExamDAO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExamControllerForUserTable {
    private int USERS_PER_PAGE = 1;
    @Autowired
    StudentExamDAO dao;
    @Autowired
    IExamService service;

    @GetMapping("/admin/users")
    public String showUsers(HttpServletRequest req, HttpSession ses) {
        req.setAttribute("content", "UserTable.jsp");
        List<Exam> exams = service.getAllExams();
        int index = Integer.parseInt(req.getParameter("chatId"));
        Exam exam = exams.get(index);
        List<StudentExam> studentExams = dao.getByExam(exam.getID());
        //for
        ses.setAttribute("studentExams",studentExams);
        return "template";
    }
}
