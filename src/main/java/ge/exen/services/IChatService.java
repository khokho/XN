package ge.exen.services;

import ge.exen.models.Chat;
import ge.exen.models.Message;

import java.util.List;

public interface IChatService {
    /**
     * @return returns chats associated with current user
     */
    List<Chat> getMyChats();

    /**
     * @param chat_id get last $limit amount of messages in chat_id
     * @return list with size <=limit of last messages
     */
    List<Message> getMessages(long chat_id, int limit);

    /**
     * @param chat_id chat to search
     * @param pattern pattern to search
     * @return list of all messages matching this pattern
     */
    List<Message> searchMessages(long chat_id, String pattern);

    /**
     * @param target_id user to chat with
     * @param exam_id exam which should be checked TODO maybe exam should be in session?
     * @return new Chat(success) or null(not successful)
     */
    Chat startChat(long target_id, long exam_id);

    //TODO add message sending methods
}
