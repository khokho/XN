package ge.exen.controllers;


import ge.exen.models.Exam;
import ge.exen.models.ExamLecturers;
import ge.exen.models.StudentExam;
import ge.exen.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamControllerForAdmin {
    private int EXAMS_PER_PAGE = 1;
    @Autowired
    IExamService examService;

    @GetMapping(value = "/admin/list")
    public String toAdminHomePage(HttpServletRequest req, HttpSession ses) {
        List<Exam> exams = examService.getAllExams();
        List<ExamInfo> list = new ArrayList<>();
        int index = 1;
        if(req.getParameter("pageNum") != null) index = Integer.parseInt(req.getParameter("pageNum"));
        for(int i =(index-1)* EXAMS_PER_PAGE; i <index*EXAMS_PER_PAGE; i++){
            if(i == exams.size()) break;
            list.add(new ExamInfo(exams.get(i),examService.isCurrentlyLive(exams.get(i))));
        }

        int pageNum = exams.size() / EXAMS_PER_PAGE+1;
        ses.setAttribute("list", list);
        req.setAttribute("current",index);
        req.setAttribute("list",list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("content", "ExamsView.jsp");
        return "template";
    }

    @PostMapping(value = "/admin/list")
    public String renderExams(HttpServletRequest req,HttpSession ses) {
        List<Exam> exams = examService.getAllExams();
        List<ExamInfo> list = new ArrayList<>();
        int index = Integer.parseInt(req.getParameter("pageNum"));
        for(int i =(index-1)* EXAMS_PER_PAGE; i <index*EXAMS_PER_PAGE; i++){
            list.add(new ExamInfo(exams.get(i),examService.isCurrentlyLive(exams.get(i))));
        }
        ses.setAttribute("list",list);
        req.setAttribute("current",index);
        return "";
    }


    public class ExamInfo {
        private Exam exam;
        private boolean isCurrentlyOn;
        private List<ExamLecturers> lecturer;
        private List<StudentExam> students;
        

        public ExamInfo(Exam exam, boolean isCurrentlyOn) {
            this.exam = exam;
            this.isCurrentlyOn = isCurrentlyOn;
        }

        public Exam getExam() {
            return exam;
        }

        public void setExam(Exam exam) {
            this.exam = exam;
        }

        public boolean isCurrentlyOn() {
            return isCurrentlyOn;
        }

        public void setCurrentlyOn(boolean currentlyOn) {
            isCurrentlyOn = currentlyOn;
        }
    }

}
