package ge.exen.controllers;



import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import ge.exen.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class newExam {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }
    @Autowired
    ExamService examFactory;

    @GetMapping("/newExam")
    public String getInfo(HttpSession session) {
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("content", "examForm.jsp");
        session.setAttribute("title", "ახალი გამოცდის შექმნა");
        return "template";
    }


    @PostMapping(value = "/newExam")
    public String retreviveForm(@RequestParam Integer variants,
                                @RequestParam String fullName,
                                @RequestParam String startDate,
                                @RequestParam Integer duration_hr,
                                @RequestParam Integer duration_mn,
                                MultipartHttpServletRequest req) {
        List<MultipartFile> rawFiles = new ArrayList<>();
        Map<String, MultipartFile> files = req.getFileMap();
        ExamDTO values = new ExamDTO();

        values.setVariants(variants.toString());
        values.setStartDate(startDate);
        values.setFullName(fullName);
        values.setHours(duration_hr.toString());
        values.setMinutes(duration_mn.toString());

        for (int i = 0; i < values.getVariants(); i++) {
            rawFiles.add(null);
        }

        for (Map.Entry<String, MultipartFile> ent : files.entrySet()) {
            if (ent.getKey().contains("statement ")) {
                int idx = Integer.parseInt(ent.getKey().substring(10)) - 1;
                rawFiles.set(idx, ent.getValue());
            }
        }
        long ID = examFactory.process(values);
        if(ID > 0) {
            examFactory.setFiles(files, ID);
        }
        return "/template";
    }
}
