package ge.exen.services;

import ge.exen.DAO.*;
import ge.exen.models.Chat;
import ge.exen.models.Message;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatService implements IChatService {
    @Autowired
    private IUserService userService;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    ChatSecurityService chatSecurityService;

    @Autowired
    ExamService examService;

    @Override
    public List<Chat> getMyChats() {
        return chatDAO.getUserChats(userService.getCurrentUser().getId());
    }

    @Override
    public List<Message> getMessages(long chatId, int from, int to) {
        User user = userService.getCurrentUser();
        chatSecurityService.validateUserChatSubscription(user.getId(), chatId);
        return messageDAO.getMessagesByRange(chatId, from, to);
    }

    @Override
    public List<Message> searchMessages(long chatId, String pattern) {
        User user = userService.getCurrentUser();
        chatSecurityService.validateUserChatSubscription(user.getId(), chatId);
        return messageDAO.getMessageByChatAndText(chatId, pattern);
    }

    @Override
    public Chat startChat(long lectId) {
        User user = userService.getCurrentUser();
        if(!user.getStatus().equals(User.STUDENT)) return null;
        if(examService.getExamForCurrentUser() == null) return null;

        return null;
    }
}
