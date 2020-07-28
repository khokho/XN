package ge.exen.DAO;

import ge.exen.configs.GlobalConstants;
import ge.exen.models.Message;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageSQLDAO extends AbstractSQLDAO implements MessageDAO {

    /**
     * @param rs ResultSet coming from message table
     * @return message read from rs
     * @throws SQLException if invalid ResultSet
     */
    private Message resultSetToMessage(ResultSet rs) throws SQLException {
        Message msg = new Message();

        msg.setMessageId(rs.getLong(1));
        msg.setFrom(rs.getLong(2));
        msg.setChatId(rs.getLong(3));
        msg.setSentDate(rs.getTimestamp(4));
        msg.setText(rs.getString(5));
        msg.setType(rs.getString(6));

        return msg;
    }

    @Override
    public boolean create(Message msg) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("INSERT into message (from_id, chat_id, sent_date, text, type) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, msg.getFrom());
            ps.setLong(2, msg.getChatId());
            ps.setTimestamp(3, msg.getSentDate());
            ps.setString(4, msg.getText());
            ps.setString(5, msg.getType());

            int changed = ps.executeUpdate();
            if (changed == 0) {
                throw new SQLException("Message couldn't be inserted");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            msg.setChatId(keys.getInt(1));
            if(GlobalConstants.DEBUG)
                System.out.println("MESSAGE WITH ID" + msg.getMessageId());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            msg.setMessageId(-1);
            return false;
        }
    }

    @Override
    public Message get(long messageId) {
        PreparedStatement ps;
        try {

            ps = conn.prepareStatement("SELECT * FROM message where message_id = ?");
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

            ps = conn.prepareStatement("SELECT * FROM message where chat_id = ?");
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

    @Override
    public List<Message> getMessagesByRange(Long chatId, int from, int to){
        List<Message> ans = new ArrayList<>();
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM message WHERE chat_id = ? order by sent_date desc limit ?, ?");
            prStmt.setLong(1, chatId);
            prStmt.setLong(2, from);
            prStmt.setLong(3, to - from + 1);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()){
                ans.add(resultSetToMessage(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return ans;
    }

    @Override
    public List<Message> getMessagesWithText(Long chatId, String pattern) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("SELECT * FROM message where chat_id = ? AND text LIKE ? AND type = ?");
            ps.setLong(1, chatId);
            ps.setString(2, "%" + pattern + "%");
            ps.setString(3, "text");
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
