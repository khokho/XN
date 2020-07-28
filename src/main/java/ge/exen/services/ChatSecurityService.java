package ge.exen.services;

import ge.exen.DAO.ChatSQLDAO;
import ge.exen.DAO.UserSQLDAO;
import ge.exen.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatSecurityService {

    public final static int INVALID_TOPIC = -1;
    public final static int CHAT_TOPIC = 0;



    @Autowired
    private UserSQLDAO userSQLDAO;

    @Autowired
    private ChatSQLDAO chatSQLDAO;

    /**
     * @param userId id of user requesting to subscribe
     * @param chatId requested chat id
     * @return true if user with userId is allowed in chat
     */
    public boolean validateUserChatSubscription(long userId, long chatId) {
        Chat chat = chatSQLDAO.getById(chatId);
        System.out.println("userId:" + userId + " going in on chat with " + chat.getstudentId() + "  " + chat.getlectorId());
        return userId == chat.getstudentId() || userId == chat.getlectorId();
    }



}
