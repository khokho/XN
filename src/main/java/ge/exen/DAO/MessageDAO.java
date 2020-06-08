package ge.exen.DAO;

import ge.exen.models.Message;

import java.util.List;

public interface MessageDAO {

    /**
     * @param msg Message to add in DB
     *            when successful sets msg.messageId after insert
     *            when fails id is -1
     */
    void create(Message msg);

    /**
     * @param messageId get Message with a messageId
     */
    Message get(long messageId);

    /**
     * @param chatId chat ID
     * @return list of messages associated with this chat
     */
    List<Message> getChatMessages(long chatId);
    //TODO List<Message> searchMessageInChar(long chatId);
    //TODO List<Message> editMessage(long chatId);
}
