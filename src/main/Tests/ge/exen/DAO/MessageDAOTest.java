package ge.exen.DAO;

import ge.exen.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;


public class MessageDAOTest {

    MessageDAO dao;

    @BeforeEach
    public void setUp() {
        System.out.println("running");
        dao = new MessageSQLDAO();
    }

    @Test
    public void create() {
        Message msg = new Message();
        msg.setFrom(5L);
        msg.setChatId(10);
        msg.setSentDate(Date.valueOf("2020-8-8"));
        msg.setText("vauza");
        msg.setType("text");
        dao.create(msg);
    }

    @Test
    public void get() {
    }

    @Test
    public void getChatMessages() {
    }
}