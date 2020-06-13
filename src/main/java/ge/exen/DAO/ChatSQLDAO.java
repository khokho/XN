package ge.exen.DAO;

import ge.exen.models.Chat;
import org.springframework.stereotype.Controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatSQLDAO extends AbstractSQLDAO implements ChatDAO {

    @Override
    public void create(Chat chat) {
        PreparedStatement prStmt;
        try {

            prStmt = db.getConnection().prepareStatement("INSERT INTO chat ('User1', 'User2') VALUES(?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            prStmt.setLong(1, chat.getUser1());
            prStmt.setLong(2, chat.getUser2());
            int executed = prStmt.executeUpdate();

            if (executed == 0) {
                ResultSet rs = prStmt.getGeneratedKeys();
                rs.next();
                chat.setChatId(rs.getLong(1));
            } else {
                throw new SQLException("Chat could not be added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            chat.setChatId(-1);
        }
    }

    @Override
    public Chat get(long chatId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM chat WHERE chat_id = ?");
            prStmt.setLong(1, chatId);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Chat with given chatId could not be retrieved.");
            }
            return resultSetToChat(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Chat> getUserChats(long userId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM chat WHERE 'User1' = ? OR 'User2' = ?");
            prStmt.setLong(1, userId);
            prStmt.setLong(2, userId);
            ResultSet rs = prStmt.executeQuery();

            List<Chat> chats = new ArrayList<>();
            while (rs.next()) {
                chats.add(resultSetToChat(rs));
            }
            return chats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Given a ResultSet, returns corresponding Chat.
     *
     * @param rs ResultSet coming from chat table
     * @return Chat representing the current row in the ResultSet
     * @throws SQLException if the ResultSet is not valid.
     */
    private Chat resultSetToChat(ResultSet rs) throws SQLException {
        Chat chat = new Chat();
        chat.setChatId(rs.getLong(1));
        chat.setUser1(rs.getLong(2));
        chat.setUser2(rs.getLong(3));
        return chat;
    }
}
