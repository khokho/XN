package ge.exen.listeners;

import ge.exen.models.Message;
import ge.exen.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatListener implements IChatListener{

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    

    public ChatListener() {
        chatService.registerChatListener(this);
    }

    public void messageReceived(Message msg){
        System.out.println(msg);
        messagingTemplate.convertAndSend("/topic/chat-"+msg.getChatId(), msg);
    }

}
