package ge.exen.controllers;


import ge.exen.dto.ExamDTO;
import ge.exen.services.ExamService;
import ge.exen.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class newExam {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }
    @Autowired
    private IExamService examFactory;

    @GetMapping("/admin/newExam")
    public String getInfo(HttpSession session) {
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("content", "examForm.jsp");
        session.setAttribute("title", "ახალი გამოცდის შექმნა");
        return "template";
    }


    @PostMapping(value = "/admin/newExam")
    public String retreviveForm(ExamDTO values,
                                MultipartHttpServletRequest req) {
        List<MultipartFile> rawFiles = new ArrayList<>();
        Map<String, MultipartFile> files = req.getFileMap();
/*        ExamDTO values = new ExamDTO();

        values.setVariants(""+variants);
        values.setStartDate(startDate);
        values.setFullName(fullName);
        values.setHours(duration_hr.toString());
        values.setMinutes(duration_mn.toString());*/

        System.out.println("111");
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
