package ge.exen.DAO;

import ge.exen.models.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageSQLDAO extends AbstractSQLDAO implements MessageDAO {

    /**
     * @param rs ResultSet coming from message table
     * @return message read from rs
     * @throws SQLException if invalid ResultSet
     */
    private Message resultSetToMessage(ResultSet rs) throws SQLException {
        Message msg = new Message();

        msg.setChatId(rs.getLong(1));
        msg.setFrom(rs.getLong(2));
        msg.setSentDate(rs.getDate(3));
        msg.setText(rs.getString(4));
        msg.setType(rs.getString(5));

        return msg;
    }

    @Override
    public void create(Message msg) {
        PreparedStatement ps;
        try {
            ps = db.getConnection().prepareStatement("INSERT into message (`from`, chat_id, sent_date, text, type) VALUES (?, ?, ?, ?, ?)");
            ps.setLong(1, msg.getFrom());
            ps.setLong(2, msg.getChatId());
            ps.setDate(3, msg.getSentDate());
            ps.setString(4, msg.getText());
            ps.setString(5, msg.getType());

            int changed = ps.executeUpdate();
            if (changed != 0) {
                throw new SQLException("Message couldn't be inserted");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            msg.setChatId(keys.getInt(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            msg.setMessageId(-1);
        }
    }

    @Override
    public Message get(long messageId) {
        PreparedStatement ps;
        try {

            ps = db.getConnection().prepareStatement("SELECT * FROM message where message_id = ?");
            ps.setLong(1, messageId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Message couldn't be retrieved");
            }
            return resultSetToMessage(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getChatMessages(long chatId) {
        PreparedStatement ps;
        try {

            ps = db.getConnection().prepareStatement("SELECT * FROM message where chat_id = ?");
            ps.setLong(1, chatId);
            ResultSet rs = ps.executeQuery();
            List<Message> messages = new ArrayList<>();
            while (rs.next()) {
                messages.add(resultSetToMessage(rs));
            }
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
