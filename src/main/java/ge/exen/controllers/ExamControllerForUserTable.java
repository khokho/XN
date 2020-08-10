package ge.exen.controllers;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.Utils.JavaMailUtil;
import ge.exen.dto.ExamLecturersRegisterDTO;
import ge.exen.dto.StudentExamRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import ge.exen.services.ExamLecturerService;
import ge.exen.services.IExamService;
import ge.exen.services.IStudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    ExamDao examdao;

    @Autowired
    ExamLecturerService eService;

    @GetMapping("/admin/users")
    public String showUsers(HttpServletRequest req, HttpSession ses) {
        req.setAttribute("content", "UserTable.jsp");
        long index = Long.parseLong(req.getParameter("examId"));
        List<User> gotStudents = studentExamsService.getUsersByExamId(index);
        int listIndex = 1;
        long examId = index;
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
        long variants = examdao.get(examId).getVariants();
        ses.setAttribute("variants", variants);
        System.out.println(variants);
        return "template";
    }

    @PostMapping("/admin/users")
    public String showUsersPost(HttpServletRequest req, HttpSession ses) {
        return showUsers(req, ses);
    }


    @GetMapping("/admin/removeUser")
    public String removeAndReturnToView(HttpServletRequest req, HttpSession ses) {
        long examId = Long.parseLong(req.getParameter("examId"));
        long removeUserId = Long.parseLong(req.getParameter("index"));
        dao.remove(removeUserId, examId);
        System.out.println(dao.getByExam(examId).size());
        return showUsers(req, ses);
        //return "/login";
    }

    @GetMapping("/admin/sendMails")
    public String sendMails(HttpServletRequest req, HttpSession ses) {
        List<User> students = (List<User>)ses.getAttribute("students");
        long examId = Long.parseLong(req.getParameter("examId"));
        for(User user : students) {
            JavaMailUtil.sendMail(user.getEmail(),"საგამოცდო რეგისტრაცია",getExamText(user,examId));
        }
        return showUsers(req, ses);
    }
    private String getExamText(User user,long examId) {
        StudentExam exam = dao.get(user.getId(),examId);
        Exam xm = examdao.get(examId);
        return "გამარჯობა " + user.getName() + " " + user.getLastName() + " გიგზავნით " + xm.getFullName() + " რეგისტრაციას  +\n" +
                " თარიღი: " + xm.getStartDate() + " \n ხანგძლივობა: " + xm.getDurationInMinutes() + " წთ \n ვარიანტი : " +exam.getVariant() + "\n ადგილი: " + exam.getCompIndex();
    }

    @GetMapping(value = "/admin/newStudentToExam")
    public String addStudentToExam(HttpServletRequest req, HttpSession session) {
        req.setAttribute("content", "add_student_to_exam.jsp");
        Exam exam = examdao.get((long)session.getAttribute("examId"));
        req.setAttribute("title", exam.getFullName()+" გამოცდაზე სტუდენტის დამატება");
        return "template";
    }
    @GetMapping(value = "/admin/newLecturerToExam")
    public String addLecturerToExam(HttpServletRequest req, HttpSession session) {
        req.setAttribute("content", "add_lecturer_to_exam.jsp");
        Exam exam = examdao.get((long)session.getAttribute("examId"));
        req.setAttribute("title", exam.getFullName()+" გამოცდაზე ლექტორის დამატება");
        return "template";
    }

    @PostMapping(value = "/admin/newStudentToExam")
    public RedirectView addStudentToExam(StudentExamRegisterDTO dto,
                                         HttpServletRequest req){
        System.out.println("sdsds");
        studentExamsService.assignStudentToExam(dto);
        return new RedirectView("/admin/list");
    }
    @PostMapping(value = "/admin/newLecturerToExam")
    public RedirectView addLecturerToExam(ExamLecturersRegisterDTO dto,
                                          HttpServletRequest req){
        System.out.println(dto.toString());
        eService.assignLecturerToExam(dto);
        return new RedirectView("/admin/list");
    }

    @GetMapping(value ="/admin/seeStudentExam")
    public String getStudentExam(HttpServletRequest req, HttpSession session){
        long examId = Long.parseLong(req.getParameter("examId"));
        long studentId = Long.parseLong(req.getParameter("studentId"));
        StudentExam studentExam = dao.get(studentId, examId);
        session.setAttribute("data", studentExam);
        req.setAttribute("content", "current_studentexam.jsp");
        req.setAttribute("title", "საგამოცდო რეგისტრაცია");
        return "template";
    }

    @PostMapping(value ="/admin/seeStudentExam")
    public RedirectView changeComputer(HttpServletRequest req, HttpSession session){
        long examId = Long.parseLong(req.getParameter("examId"));
        long studentId = Long.parseLong(req.getParameter("studentId"));
        long compIndex = Long.parseLong(req.getParameter("compIndex"));
        dao.changeComputer(studentId, examId, compIndex);
        return new RedirectView("/admin/list");
    }

}
