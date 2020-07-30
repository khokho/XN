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
        List<Pair> list = new ArrayList<>();
        for(int i = 0; i < exams.size(); i++){
            list.add(new Pair(exams.get(i),examService.isCurrentlyLive(exams.get(i))));
        }
        int pageNum = exams.size() / EXAMS_PER_PAGE + 1;
        req.setAttribute("list", list);
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


    public class Pair {
        private Exam exam;
        private boolean isCurrentlyOn;

        public Pair(Exam exam, boolean isCurrentlyOn) {
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
