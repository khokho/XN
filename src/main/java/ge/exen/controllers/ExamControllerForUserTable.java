package ge.exen.controllers;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.StudentExamRegisterDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import ge.exen.services.IExamService;
import ge.exen.services.IStudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamControllerForUserTable {
    private int USERS_PER_PAGE = 5;
    @Autowired
    IExamService service;
    @Autowired
    IStudentExamService studentExamsService;
    @Autowired
    StudentExamDAO dao;
    @Autowired
    ExamDao examDao;

    @GetMapping("/admin/users")
    public String showUsers(HttpServletRequest req, HttpSession ses) {
        req.setAttribute("content", "UserTable.jsp");
        long index = Long.parseLong(req.getParameter("examId"));
        long examId = index;
        List<User> gotStudents = studentExamsService.getUsersByExamId(index);
        int listIndex = 1;
        if (req.getParameter("pageNum") != null) listIndex = Integer.parseInt(req.getParameter("pageNum"));
        List<User> students = new ArrayList<>();
        for (int i = (listIndex - 1) * USERS_PER_PAGE; i < listIndex * USERS_PER_PAGE; i++) {
            if (i == gotStudents.size()) break;
            students.add(gotStudents.get(i));
        }
        int pageNum = students.size() / USERS_PER_PAGE + 1;
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("current", listIndex);
        ses.setAttribute("students", students);
        ses.setAttribute("examId", examId);
        long variants = examDao.get(examId).getVariants();
        ses.setAttribute("variants", variants);
        System.out.println(variants);
        return "template";
    }

    @PostMapping("/admin/users")
    public String showUsersPost(HttpServletRequest req, HttpSession ses) {
        return showUsers(req, ses);
    }

    @GetMapping(value = "/admin/newStudentexam")
    public String addStudentExam(HttpServletRequest req, HttpSession session) {
        req.setAttribute("content", "add_studentexam.jsp");
        System.out.println((long)session.getAttribute("examId"));
        return "template";
    }

    @PostMapping(value = "/admin/newStudentexam")
    public String login(StudentExamRegisterDTO dto,
                        HttpServletRequest req,
                        HttpServletResponse resp){
        studentExamsService.assignStudentToExam(dto);
        req.setAttribute("content", "UserTable.jsp");
        return "/template";
    }

}