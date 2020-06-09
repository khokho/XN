package ge.exen.controllers;



import ge.exen.models.ExamFactory;
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

        for (int i = 0; i < variantCount; i++) {
            rawFiles.add(null);
        }

        for (Map.Entry<String, MultipartFile> ent : files.entrySet()) {
            if (ent.getKey().contains("statement ")) {
                int idx = Integer.parseInt(ent.getKey().substring(10)) - 1;
                rawFiles.set(idx, ent.getValue());
            }
        }
        String message = examFactory.process(name, startDt, durHours, durMinutes, variantStr, rawFiles);
        System.out.println(message);
        return "/newExam";
    }
}
