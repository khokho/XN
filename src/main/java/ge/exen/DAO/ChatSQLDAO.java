package ge.exen.DAO;

import ge.exen.models.Chat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatSQLDAO extends AbstractSQLDAO implements ChatDAO {

    @Override
    public void create(Chat chat) {
        try {

            PreparedStatement prStmt = conn.prepareStatement("INSERT INTO chat (student_id, lector_id, exam_id) VALUES(?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            prStmt.setLong(1, chat.getstudentId());
            prStmt.setLong(2, chat.getlectorId());
            prStmt.setLong(3, chat.getExamId());
            int executed = prStmt.executeUpdate();

            if (executed == 0) {
                throw new SQLException("Chat could not be added.");
            } else {
                ResultSet rs = prStmt.getGeneratedKeys();
                rs.next();
                chat.setChatId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            chat.setChatId(-1);
        }
    }

    @Override
    public Chat getById(long chatId) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM chat WHERE chat_id = ?");
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
    public Chat get(long studentId, long lectorId, long examId) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM chat WHERE student_id = ? AND lector_id = ? AND exam_id = ?");
            prStmt.setLong(1, studentId);
            prStmt.setLong(2, lectorId);
            prStmt.setLong(3, examId);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Chat with given data could not be retrieved.");
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
            prStmt = conn.prepareStatement("SELECT * FROM chat WHERE student_id = ? OR lector_id = ?");
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
        chat.setstudentId(rs.getLong(2));
        chat.setlectorId(rs.getLong(3));
        chat.setExamId(rs.getLong(4));
        return chat;
    }
}
