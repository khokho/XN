package ge.exen.controllers;

import ge.exen.configs.GlobalConstants;
import ge.exen.dto.SendMessageDTO;
import ge.exen.models.Message;
import ge.exen.models.UserPrincipal;
import ge.exen.services.ChatSecurityService;
import ge.exen.services.IChatService;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatSecurityService chatSecurityService;

    @Autowired
    private IChatService chatService;

    //TODO this is not final
    @GetMapping("/chat/{chatId}")
    public String getChat(@PathVariable long chatId, Model model) {
//        UserLoginDTO login = new UserLoginDTO();
//        login.setEmail("bgame19@freeuni.edu.ge");
//        login.setPassword("gambit");
//        userService.login(login);

        model.addAttribute("chatId", chatId);
        model.addAttribute("userId", userService.getCurrentUser().getId());
        model.addAttribute("content", "chat.jsp");
        return "/template";
    }

    @ResponseBody
    @GetMapping(value = "/getMessages/{chatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessages(@PathVariable long chatId, int from, int to){
        return chatService.getMessages(chatId, from, to);
    }


    @MessageMapping("/chat-{chatId}")
    public void receiveMessage(@DestinationVariable long chatId,
                               UserPrincipal user,
                               @Payload String message){
        long userId = user.getId();
        if(GlobalConstants.DEBUG) {
            System.out.println();
            System.out.println("recieved message from: " + userId);
            System.out.println("chat id: " + chatId);
        }
        if(GlobalConstants.DEBUG){
            System.out.println("Security passed");
            System.out.println("message: " + message);
        }

        SendMessageDTO msg = new SendMessageDTO();
        msg.setChatId(chatId);
        msg.setText(message);
        msg.setType("text");
        chatService.sendMessage(msg, userId);
    }

}
