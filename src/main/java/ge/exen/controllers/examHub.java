package ge.exen.controllers;

import ge.exen.DAO.UserUploadDAO;
import ge.exen.models.Exam;
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
import javax.servlet.http.HttpSession;
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
        req.setAttribute("content", "examHub.jsp");
        req.setAttribute("title", "გამოცდა");
        StudentExam exam = exams.getExamForCurrentUser();
        System.out.println(uploadDAO.getForUser(exam).size());
        String link = material.getMaterial(exam.getVariant(), exam.getExamId());
        System.out.println(link);
        req.setAttribute("statement", link);
        req.setAttribute("links", uploadDAO.getForUser(exams.getExamForCurrentUser()));
        return "template";
    }

    @PostMapping("/eh")
    public String handleUpload(@RequestParam("sol") MultipartFile file,HttpServletRequest req) {
        if(!file.isEmpty()) {
            Upload upload = new Upload();
            upload.setFromId(users.getCurrentUser().getId());
            upload.setExamId(exams.getExamForCurrentUser().getExamId());
            upload.setVarId(exams.getExamForCurrentUser().getVariant());
            upload.setTime(new Timestamp(new Date().getTime()));
            factory.Process(file, upload);
        }
        req.setAttribute("content", "examHub.jsp");
        req.setAttribute("title", "გამოცდა");
        System.out.println(uploadDAO.getForUser(exams.getExamForCurrentUser()).size());
        req.setAttribute("links", uploadDAO.getForUser(exams.getExamForCurrentUser()));

        return "template";
    }
}
