package ge.exen.controllers;

import ge.exen.dto.SendMessageDTO;
import ge.exen.services.IChatService;
import ge.exen.services.IFileWorker;
import ge.exen.services.IUserService;
import ge.exen.services.RandomNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Controller
public class chatImage {

    private static final String DIR = "src/main/webapp/resources/images";
    private static final String VIEW_DIR = "resources/images";



    @Autowired
    private IChatService chat;

    @Autowired
    private IFileWorker fileWorker;

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/sendImage")
    public String getImage(@RequestParam String chat_id,
                           MultipartHttpServletRequest req){
        Map<String, MultipartFile> files = req.getFileMap();
        for (Map.Entry<String, MultipartFile> ent : files.entrySet()) {
            SendMessageDTO dto = new SendMessageDTO();
            dto.setChatId(Integer.parseInt(chat_id));
            dto.setType(SendMessageDTO.IMAGE);

            String name = RandomNameGenerator.generate(10);
            fileWorker.storeMultiPartFile(DIR, ent.getValue(), name);

            dto.setText(VIEW_DIR + "/" + name);
            chat.sendMessage(dto, userService.getCurrentUser().getId());
        }

        return "";
    }
}
