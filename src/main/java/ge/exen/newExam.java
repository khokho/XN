package ge.exen;

import ge.exen.Model.ExamFactory;


import ge.exen.Model.FileWorker;
import ge.exen.Objects.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import java.util.*;

@Controller
public class newExam {

    @Autowired
    ExamFactory examFactory;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

    @GetMapping("/newExam")
    public String getInfo() {
        return "newExam";
    }


    @PostMapping("/newExam")
    public String retreviveForm(MultipartHttpServletRequest req,
                                @RequestParam(name = "variants") String variantStr,
                                @RequestParam(name = "fullName") String name,
                                @RequestParam(name = "startDate") String startDt,
                                @RequestParam(name = "hours") String durHours,
                                @RequestParam(name = "minutes") String durMinutes) {
        int variantCount = Integer.parseInt(variantStr);
        List<MultipartFile> rawFiles = new ArrayList<>();
        Map<String, MultipartFile> files = req.getFileMap();

        for (int i = 0; i <= variantCount; i++) {
            rawFiles.add(null);
        }

        for (Map.Entry<String, MultipartFile> ent : files.entrySet()) {
            if (ent.getKey().contains("statement ")) {
                System.out.println("!>");
                int idx = Integer.parseInt(ent.getKey().substring(10));
                rawFiles.set(idx, ent.getValue());
            }
        }
        long code = examFactory.process(name, startDt, durHours, durMinutes, variantStr, rawFiles);

        if (code == ExamFactory.STATUS_ERR) System.out.println("ERROR");
        if (code == ExamFactory.STATUS_DB_ERR) System.out.println("DB ERROR");
        if (code == ExamFactory.STATUS_FILE_DB_ERR) System.out.println("File storing error");
        return "/newExam";
    }
}
