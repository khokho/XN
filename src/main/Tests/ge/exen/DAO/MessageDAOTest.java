package ge.exen.DAO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class MessageDAOTest {

    @Autowired
    MessageDAO messageSQLDAO;


    @Test
    public void create() {
        //TODO
       /* Message msg = new Message();
        msg.setFrom(0);
        msg.setChatId(0);
        msg.setSentDate(Date.valueOf("2020-8-8"));
        msg.setText("wowza");
        msg.setType("text");
        messageSQLDAO.create(msg);
        System.out.println(msg.getMessageId());
        assertEquals("wowza", messageSQLDAO.get(msg.getMessageId()).getText());
    */
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