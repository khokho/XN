package ge.exen.DAO;

import ge.exen.models.Chat;

import java.util.List;

public interface ChatDAO {

    /**
     * Adds given chat in the DB and sets its chatId.
     * When chat is not added successfully chatId is -1.
     *
     * @param chat Chat to be added in the DB.
     */
    void create(Chat chat);

    /**
     * Returns a Chat with given chatId.
     * Returns null if corresponding Chat does not exist.
     *
     * @param chatId long representing the Chat's ID
     * @return Chat Chat with given chatId
     */
    Chat get(long chatId);

    /**
     * Returns given user's chats.
     * Returns null if User with userId does not exist.
     *
     * @param userId long representing the User's ID
     * @return List<Chat> Chats of the User with userId
     */
    List<Chat> getUserChats(long userId);
}
