package ge.exen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@Controller
public class chatImage {

    @PostMapping(value = "/sendImage")
    public String getImage(@RequestParam String chat_id,
                           MultipartHttpServletRequest req){

        Map<String, MultipartFile> files = req.getFileMap();
        return "";
    }
}
