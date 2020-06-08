package ge.exen.DAO;

import ge.exen.models.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MessageDAOTest {

    @Mock
    MessageDAO messageSQLDAO;

    @Test
    public void create() {
        //TODO
        Message msg = new Message();
        msg.setFrom(0);
        msg.setChatId(0);
        msg.setSentDate(Date.valueOf("2020-8-8"));
        msg.setText("wowza");
        msg.setType("text");
        messageSQLDAO.create(msg);
        System.out.println(msg.getMessageId());
        assertEquals("wowza", messageSQLDAO.get(msg.getMessageId()).getText());
    }

    @Test
    public void get() {
        //TODO
    }

    @Test
    public void getChatMessages() {
        //TODO
    }
}