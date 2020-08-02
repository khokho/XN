package ge.exen.controllers;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.DAO.UserDAO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import ge.exen.services.IExamService;
import ge.exen.services.IStudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ExamControllerForUserTable {
    private int USERS_PER_PAGE = 1;
    @Autowired
    StudentExamDAO dao;
    @Autowired
    ExamDao examDao;
    @Autowired
    UserDAO udao;
    @Autowired
    IExamService service;
    @Autowired
    IStudentExamService studentExamsService;

    @GetMapping("/admin/users")
    public String showUsers(HttpServletRequest req, HttpSession ses) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        req.setAttribute("content", "UserTable.jsp");
        List<Exam> exams = service.getAllExams();
        int index = Integer.parseInt(req.getParameter("examId"));
        Exam exam = exams.get(index);
        List<User> students = studentExamsService.getUsersByExamId(exam.getID());
        ses.setAttribute("students",students);
        return "template";
    }
}
