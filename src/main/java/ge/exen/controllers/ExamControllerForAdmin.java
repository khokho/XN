package ge.exen.controllers;


import ge.exen.DAO.ExamDao;
import ge.exen.models.Exam;
import ge.exen.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamControllerForAdmin {
    private int EXAMS_PER_PAGE = 10;
    @Autowired
    IExamService examService;
    @GetMapping(value = "/l")
    public String toAdminHomePage(HttpServletRequest req) {
        List<Exam> exams = examService.getAllExams();
        int pageNum = exams.size()/EXAMS_PER_PAGE+1;
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("content","ExamsView.jsp");
        req.setAttribute("sidebar","adminSidebar.jsp");
        return "template";
    }
    @PostMapping(value = "/r")
    public String rendeerExams(HttpServletRequest req) {
        int startIndex = 0;
        if(req.getAttribute("page") !=null)  startIndex = EXAMS_PER_PAGE*((int)req.getAttribute("page")-1);
        List<Exam> list =  new ArrayList<>();
        for(int i = startIndex; i < startIndex+EXAMS_PER_PAGE; i++) {
            list.add(examService.getAllCurrentExams().get(i));
        }
       // req.setAttribute("list",new ArrayList<Exam>().addAll(startIndex,));
        return "";
    }





}
