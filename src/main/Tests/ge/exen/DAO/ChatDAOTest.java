package ge.exen.DAO;

import ge.exen.models.Chat;
import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class ChatDAOTest {
    @Autowired
    private ChatDAO chatDAO;
    @Autowired
    UserDAO userDAO;

    private Chat chat = null;
    private long studentId;
    private long lectorId;


    @BeforeEach
    public void setUp(){
        User student = new User();
        student.setEmail("adavi18@freeuni.edu.ge");
        student.setName("Ana");
        student.setLastName("Davitashvili");
        student.setPasswordHash("passwordhash");
        student.setStatus("student");
        userDAO.create(student);

        User lector = new User();
        lector.setEmail("n.matchavariani@freeuni.edu.ge");
        lector.setName("Nikoloz");
        lector.setLastName("Matchavariani");
        lector.setPasswordHash("passwordhash");
        lector.setStatus("lector");
        userDAO.create(lector);

        studentId = student.getId();
        lectorId = lector.getId();
        chat = new Chat();
        chat.setstudentId(studentId);
        chat.setlectorId(lectorId);
        chat.setExamId(1);
    }

    @Test
    @DirtiesContext
    public void testCreateValid() {
        chatDAO.create(chat);
        assertTrue(chat.getChatId() >= 0);
    }

    @Test
    @DirtiesContext
    public void testCreateSameTwice(){
        chatDAO.create(chat);
        chatDAO.create(chat);
        assertEquals(-1, chat.getChatId());
    }

    @Test
    @DirtiesContext
    public void testCreateNullUser(){
        Chat nullUsersChat = new Chat();
        nullUsersChat.setExamId(1);
        chatDAO.create(nullUsersChat);
        assertEquals(-1, nullUsersChat.getChatId());

        Chat nullLectorChat = new Chat();
        nullLectorChat.setstudentId(1);
        nullLectorChat.setExamId(1);
        chatDAO.create(nullLectorChat);
        assertEquals(-1, nullLectorChat.getChatId());

        Chat nullStudentChat= new Chat();
        nullStudentChat.setlectorId(1);
        nullStudentChat.setExamId(1);
        chatDAO.create(nullStudentChat);
        assertEquals(-1, nullStudentChat.getChatId());
    }

    @Test
    @DirtiesContext
    public void testCreateInvalidUsers(){
        Chat invalidUsersChat = new Chat();
        invalidUsersChat.setstudentId(-2);
        invalidUsersChat.setlectorId(-43);
        invalidUsersChat.setExamId(1);
        chatDAO.create(invalidUsersChat);
        assertEquals(-1, invalidUsersChat.getChatId());

        invalidUsersChat.setstudentId(1);
        chatDAO.create(invalidUsersChat);
        assertEquals(-1, invalidUsersChat.getChatId());

        invalidUsersChat.setstudentId(-3);
        invalidUsersChat.setlectorId(1);
        chatDAO.create(invalidUsersChat);
        assertEquals(-1, invalidUsersChat.getChatId());

    }

    @Test
    @DirtiesContext
    public void testCreateInvalidExam(){
        Chat nullExamChat = new Chat();
        nullExamChat.setstudentId(1);
        nullExamChat.setlectorId(2);
        chatDAO.create(nullExamChat);
        assertEquals(-1, nullExamChat.getChatId());

        Chat invalidExamChat = new Chat();
        invalidExamChat.setstudentId(1);
        invalidExamChat.setlectorId(2);
        invalidExamChat.setExamId(-3);
        chatDAO.create(invalidExamChat);
        assertEquals(-1, invalidExamChat.getChatId());
    }

    @Test
    @DirtiesContext
    public void testGetById(){
        chatDAO.create(chat);
        long chatId = chat.getChatId();

        Chat result = chatDAO.getById(chatId);
        assertEquals(studentId, result.getstudentId());
        assertEquals(lectorId, result.getlectorId());
        assertEquals(1, result.getExamId());
    }

    @Test
    @DirtiesContext
    public void testGetByNull(){
        Chat result = chatDAO.getById(-3);
        assertNull(result);
    }

    @Test
    @DirtiesContext
    public void testGet(){
        chatDAO.create(chat);

        Chat result = chatDAO.get(studentId, lectorId, 1);
        assertTrue(result.getChatId() >= 0);
        assertEquals(studentId, result.getstudentId());
        assertEquals(lectorId, result.getlectorId());
        assertEquals(1, result.getExamId());
    }

    @Test
    @DirtiesContext
    public void testGetNull(){

        Chat result = chatDAO.get(-4, lectorId, 1);
        assertNull(result);

        result = chatDAO.get(studentId, -4, 1);
        assertNull(result);

        result = chatDAO.get(studentId, lectorId, -3);
        assertNull(result);
    }

    @Test
    @DirtiesContext
    public void testGetUserChats(){
        chatDAO.create(chat);

        chat.setlectorId(1);
        chatDAO.create(chat);

        List<Chat> res = chatDAO.getUserChats(studentId);
        assertEquals(2, res.size());

        for (Chat ch : res){
            assertEquals(ch.getstudentId(), studentId);
            assertTrue(ch.getlectorId() == lectorId || ch.getlectorId() == 1);
        }

        res = chatDAO.getUserChats(lectorId);
        assertEquals(1, res.size());
        assertEquals(studentId, res.get(0).getstudentId());
    }

    @Test
    @DirtiesContext
    public void testGetUserChatsNull(){
        List<Chat> res = chatDAO.getUserChats(-3);
        assertEquals(0, res.size());
    }
}
