package ge.exen.controllers;


import ge.exen.DAO.SQLExamDao;
import ge.exen.dto.ExamDTO;
import ge.exen.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class newExam {



    @Autowired
    private IExamService examService;

    @Autowired
    private SQLExamDao exdao;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    @GetMapping("/admin/newExam")
    public String getInfo(@Nullable Integer examId, Model model) {
        model.addAttribute("content", "examForm.jsp");
        if(examId == null)
            model.addAttribute("title", "ახალი გამოცდის შექმნა");
        else
            model.addAttribute("title", "გამოცდის პარამეტრების შეცვლა");
        if(examId != null)
            model.addAttribute("modifyingExam", exdao.get(examId));
        return "template";
    }


    @PostMapping(value = "/admin/newExam")
    public RedirectView retreviveForm(@Nullable Long examId, ExamDTO values) {
        if(examId!=null){
            examService.modifyExam(examId, values);
            return new RedirectView("/admin/list");
        }
        long ID = examService.process(values);
        return new RedirectView("/admin/list");
    }
}
