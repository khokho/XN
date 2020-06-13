package ge.exen.controllers;

import ge.exen.models.Upload;
import ge.exen.models.User;
import ge.exen.models.UserUploadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserUploader {
    @GetMapping("/upload")
    public String handler() {
        return "upload";
    }

    @Autowired
    UserUploadFactory factory;


    /**
     * @param file    uploaded file
     * @param var_id  passed parameter
     * @param session session
     * @return saves uploaded file and returns url
     */
    @PostMapping("/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, @RequestParam("var_id") int var_id, HttpSession session) {
        Upload upload = new Upload();
        upload.setFromId((Integer) session.getAttribute("from_id"));
        upload.setExamId((Integer) session.getAttribute("exam_id"));
        upload.setVarId(var_id);
        int result = UserUploadFactory.Process(file, upload);
        return "/upload";
    }
}
