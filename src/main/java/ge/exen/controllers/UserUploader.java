package ge.exen.controllers;

import ge.exen.models.Upload;
import ge.exen.services.IExamService;
import ge.exen.services.IUserService;
import ge.exen.services.UserUploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UserUploader {
    @GetMapping("/upload")
    public String handler() {
        return "upload";
    }

    @Autowired
    private UserUploadFactory factory;

    @Autowired
    private IUserService users;

    @Autowired
    private IExamService exams;

    /**
     * @param file    uploaded file
     * @param var_id  passed parameter
     * @param session session
     * @return saves uploaded file and returns url
     */

    @PostMapping("/upload")
    public boolean handleUpload(@RequestParam("file") MultipartFile file, @RequestParam("var_id") int var_id, HttpSession session) {
        Upload upload = new Upload();
        upload.setFromId(users.getCurrentUser().getId());
        upload.setExamId(exams.getLiveExamForCurrentStudent().getExamId());
        upload.setVarId(exams.getLiveExamForCurrentStudent().getVariant());
        upload.setTime(new Timestamp(new Date().getTime()));
        int result = factory.Process(file, upload);
        return true;
    }
}
