package ge.exen.controllers;

import ge.exen.models.Exam;
import ge.exen.models.ExamLecturers;
import ge.exen.models.StudentExam;
import ge.exen.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class lecturerExamList {
    private static final int EXAMS_PER_PAGE = 10;
    @Autowired
    ExamService examService;

    @GetMapping(value = "/lecturer/exams")
    public String render(HttpServletRequest req, Model model){
        List<Exam> exams = examService.getExamsForHighStatus();
        List<ExamInfo> list = new ArrayList<>();
        int index = 1;
        if(req.getParameter("pageNum") != null) index = Integer.parseInt(req.getParameter("pageNum"));
        for(int i =(index-1)* EXAMS_PER_PAGE; i <index*EXAMS_PER_PAGE; i++){
            if(i == exams.size()) break;
            list.add(new ExamInfo(exams.get(i),examService.isCurrentlyLive(exams.get(i))));
        }

        int pageNum = Math.max((exams.size()-1) / EXAMS_PER_PAGE, 0)+1;
        req.setAttribute("current",index);
        req.setAttribute("title","გამოცდები");
        req.setAttribute("list",list);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("content", "examLecturerView.jsp");

        return "template";
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
