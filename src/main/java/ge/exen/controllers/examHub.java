package ge.exen.controllers;

import ge.exen.DAO.UserUploadDAO;
import ge.exen.models.StudentExam;
import ge.exen.models.Upload;
import ge.exen.services.IExamMaterial;
import ge.exen.services.IExamService;
import ge.exen.services.IUserService;
import ge.exen.services.UserUploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class examHub {
    @Autowired
    private UserUploadFactory factory;

    @Autowired
    private IExamMaterial material;

    @Autowired
    private IUserService users;

    @Autowired
    private IExamService exams;

    @Autowired
    private UserUploadDAO uploadDAO;

    @GetMapping(value = "/eh")
    public String displayHub(HttpServletRequest req){
        StudentExam exam = exams.getLiveExamForCurrentStudent();
        if(exam == null){
            req.setAttribute("title", "გამოცდა");
            req.setAttribute("content", "wait.jsp");
            return "template";
        }
        req.setAttribute("content", "examHub.jsp");
        req.setAttribute("title", "გამოცდა");
        String link = material.getMaterial(exam.getVariant(), exam.getExamId());
        req.setAttribute("statement", link);

        req.setAttribute("links", uploadDAO.getForUser(exam));
        return "template";
    }

    @PostMapping("/eh")
    public String handleUpload(@RequestParam("sol") MultipartFile file,HttpServletRequest req) {
        StudentExam exam = exams.getLiveExamForCurrentStudent();
        if(exam == null){
            req.setAttribute("title", "გამოცდა");
            req.setAttribute("content", "wait.jsp");
            return "template";
        }
        if(!file.isEmpty()) {
            Upload upload = new Upload();
            upload.setFromId(users.getCurrentUser().getId());
            upload.setExamId(exam.getExamId());
            upload.setVarId(exam.getVariant());
            upload.setTime(new Timestamp(new Date().getTime()));
            factory.Process(file, upload);
        }
        req.setAttribute("content", "examHub.jsp");
        req.setAttribute("title", "გამოცდა");
        System.out.println(uploadDAO.getForUser(exam).size());
        req.setAttribute("links", uploadDAO.getForUser(exam));

        return "template";
    }
}
