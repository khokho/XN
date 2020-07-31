package ge.exen.controllers;


import ge.exen.DAO.ExamDao;
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
    private int EXAMS_PER_PAGE = 10;
    @Autowired
    IExamService examService;

    @GetMapping(value = "/l")
    public String toAdminHomePage(HttpServletRequest req, HttpSession ses) {
        List<Exam> exams = examService.getAllExams();
        List<ExamInfo> list = new ArrayList<>();
        for(int i = 0; i < exams.size(); i++){
            list.add(new ExamInfo(exams.get(i),examService.isCurrentlyLive(exams.get(i))));
        }
        int pageNum = exams.size() / EXAMS_PER_PAGE + 1;
        req.setAttribute("list", list);
        ses.setAttribute("list",list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("content", "ExamsView.jsp");
        req.setAttribute("sidebar", "adminSidebar.jsp");
        return "template";
    }

    @PostMapping(value = "/r")
    public String rendeerExams(HttpServletRequest req) {
        int startIndex = 0;
        if (req.getAttribute("page") != null) startIndex = EXAMS_PER_PAGE * ((int) req.getAttribute("page") - 1);
        List<Exam> list = new ArrayList<>();
        for (int i = startIndex; i < startIndex + EXAMS_PER_PAGE; i++) {
            list.add(examService.getAllCurrentExams().get(i));
        }
        // req.setAttribute("list",new ArrayList<Exam>().addAll(startIndex,));
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
