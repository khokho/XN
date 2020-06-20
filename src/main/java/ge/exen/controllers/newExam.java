package ge.exen.controllers;



import ge.exen.dto.ExamDTO;
import ge.exen.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import java.util.*;

@Controller
public class newExam {

    @Autowired
    ExamService examFactory;

    @GetMapping("/newExam")
    public String getInfo() {
        return "newExam";
    }


    @PostMapping("/newExam")
    public String retreviveForm(MultipartHttpServletRequest req,
                                @RequestBody ExamDTO values) {
        List<MultipartFile> rawFiles = new ArrayList<>();
        Map<String, MultipartFile> files = req.getFileMap();

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
        return "/newExam";
    }
}
