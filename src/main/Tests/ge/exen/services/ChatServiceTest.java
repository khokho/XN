package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.dto.*;
import ge.exen.models.Chat;
import ge.exen.models.ExamLecturers;
import ge.exen.models.Message;
import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration // this annotation creates sessions and stuff
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class ChatServiceTest {
    @Autowired
    IUserService userService;

    @Autowired
    IChatService chatService;

    @Autowired
    IExamService examService;

    @Autowired
    IExamLecturerService examLecturerService;

    @Autowired
    IStudentExamService studentExamService;

    @Autowired
    UserDAO userDAO;

    long lekvaId;
    long tamtaId;
    long nanaId;
    UserRegisterDTO lekva;
    UserRegisterDTO tamta;
    UserRegisterDTO nanaAdmin;
    UserLoginDTO tamtaLogin;
    UserLoginDTO nanaLogin;
    ExamLecturersRegisterDTO exam1Lekva;

    @BeforeEach
    @DirtiesContext
    public void setup(){
        nanaAdmin = new UserRegisterDTO();
        nanaAdmin.setEmail("n.kikacheishvili@freeuni.edu.ge");
        nanaAdmin.setName("Nana");
        nanaAdmin.setLastName("Kikacheishvili");
        nanaAdmin.setPassword("adminYOyoYo");
        nanaAdmin.setStatus("admin");
        //ACTION
        userService.registerNewUser(nanaAdmin);
        nanaId = userDAO.getUserByMail(nanaAdmin.getEmail()).getId();

        nanaLogin = new UserLoginDTO();
        nanaLogin.setEmail(nanaAdmin.getEmail());
        nanaLogin.setPassword(nanaAdmin.getPassword());

        lekva = new UserRegisterDTO();
        lekva.setEmail("g.lekveishvili@freeuni.edu.ge");
        lekva.setName("Giorgi");
        lekva.setLastName("Lekveishvili");
        lekva.setPassword("topcoder7834");
        lekva.setStatus("lector");
        //ACTION
        userService.registerNewUser(lekva);
        lekvaId = userDAO.getUserByMail(lekva.getEmail()).getId();

        exam1Lekva = new ExamLecturersRegisterDTO();
        exam1Lekva.setExamId(1L);
        exam1Lekva.setLecturerMail(lekva.getEmail());
        //ACTION
        userService.login(nanaLogin);
        examLecturerService.assignLecturerToExam(exam1Lekva);

        tamta = new UserRegisterDTO();
        tamta.setEmail("ttopu18@freeuni.edu.ge");
        tamta.setName("Tamta");
        tamta.setLastName("Topuria");
        tamta.setPassword("PIOpaopioPAO");
        tamta.setStatus("student");
        //ACTION
        userService.registerNewUser(tamta);
        tamtaId = userDAO.getUserByMail(tamta.getEmail()).getId();

        tamtaLogin = new UserLoginDTO();
        tamtaLogin.setEmail(tamta.getEmail());
        tamtaLogin.setPassword(tamta.getPassword());
        userService.login(nanaLogin);

        StudentExamRegisterDTO tamtaExam1 = new StudentExamRegisterDTO();
        tamtaExam1.setStudentMail(tamta.getEmail());
        tamtaExam1.setExamId(1L);
        tamtaExam1.setCompIndex(133L);
        tamtaExam1.setVariant(1L);
        //ACTION
        userService.login(nanaLogin);
        boolean bb1 = studentExamService.assignStudentToExam(tamtaExam1);
        bb1 = false;
    }

    @Test
    @DirtiesContext
    void startChat() {
        boolean bb = userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        assertNotNull(chatLekvaTamta);
        assertEquals(1, chatLekvaTamta.getExamId());
        assertEquals(lekvaId, chatLekvaTamta.getlectorId());
        assertEquals(tamtaId, chatLekvaTamta.getstudentId());
        assertNull(chatService.startChat(nanaId));
    }


    @Test
    @DirtiesContext
    void getMyChats() {
        userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        List<Chat> chats = chatService.getMyChats();
        assertEquals(1, chats.size());
        assertEquals(lekvaId, chats.get(0).getlectorId());
        assertEquals(tamtaId, chats.get(0).getstudentId());
        assertEquals(1, chats.get(0).getExamId());
    }

    @Test
    @DirtiesContext
    void sendMessage(){
        userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        SendMessageDTO tamtaLekvaMessage = new SendMessageDTO();
        tamtaLekvaMessage.setChatId(chatLekvaTamta.getChatId());
        tamtaLekvaMessage.setType("text");
        tamtaLekvaMessage.setText("Gamarjoba, pirvel amocanashi uaryofiti ricxvebic unda gavitvaliwinot?");
        assertTrue(chatService.sendMessage(tamtaLekvaMessage, tamtaId));
    }

    @Test
    @DirtiesContext
    void getMessagesOneMessage() {
        userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        SendMessageDTO tamtaLekvaMessage = new SendMessageDTO();
        tamtaLekvaMessage.setChatId(chatLekvaTamta.getChatId());
        tamtaLekvaMessage.setType("text");
        tamtaLekvaMessage.setText("Gamarjoba, pirvel amocanashi uaryofiti ricxvebic unda gavitvaliwinot?");
        assertTrue(chatService.sendMessage(tamtaLekvaMessage, tamtaId));

        List<Message> messages = chatService.getMessages(tamtaLekvaMessage.getChatId(), 0, 100);
        assertEquals(1, messages.size());
        assertEquals(chatLekvaTamta.getChatId(), messages.get(0).getChatId());
        assertEquals(tamtaId, messages.get(0).getFrom());
        assertEquals(tamtaLekvaMessage.getText(), messages.get(0).getText());

        SendMessageDTO LekvaTamtaMessage = new SendMessageDTO();
        LekvaTamtaMessage.setChatId(chatLekvaTamta.getChatId());
        LekvaTamtaMessage.setType("text");
        LekvaTamtaMessage.setText("Ar aris sawiro;)");
        assertTrue(chatService.sendMessage(LekvaTamtaMessage, lekvaId));

        List<Message> messages2 = chatService.getMessages(tamtaLekvaMessage.getChatId(), 0, 2);
        assertEquals(2, messages2.size());
        assertEquals(chatLekvaTamta.getChatId(), messages2.get(1).getChatId());
        assertEquals(lekvaId, messages2.get(1).getFrom());
        assertEquals(LekvaTamtaMessage.getText(), messages2.get(1).getText());

        List<Message> messages3 = chatService.getMessages(tamtaLekvaMessage.getChatId(), 1, 1);
        assertEquals(1, messages3.size());
        assertEquals(chatLekvaTamta.getChatId(), messages3.get(0).getChatId());
        assertEquals(lekvaId, messages3.get(0).getFrom());
        assertEquals(LekvaTamtaMessage.getText(), messages3.get(0).getText());
    }

    @Test
    @DirtiesContext
    void searchMessages() {
        userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        SendMessageDTO tamtaLekvaMessage = new SendMessageDTO();
        tamtaLekvaMessage.setChatId(chatLekvaTamta.getChatId());
        tamtaLekvaMessage.setType("text");
        tamtaLekvaMessage.setText("Gamarjoba, pirvel amocanashi uaryofiti ricxvebic unda gavitvaliwinot?");
        assertTrue(chatService.sendMessage(tamtaLekvaMessage, tamtaId));

        SendMessageDTO LekvaTamtaMessage = new SendMessageDTO();
        LekvaTamtaMessage.setChatId(chatLekvaTamta.getChatId());
        LekvaTamtaMessage.setType("text");
        LekvaTamtaMessage.setText("Ar aris sawiro;)");
        assertTrue(chatService.sendMessage(LekvaTamtaMessage, lekvaId));

        List<Message> messages = chatService.searchMessages(chatLekvaTamta.getChatId(), "uaryofiti");
        assertEquals(1, messages.size());
        assertEquals(chatLekvaTamta.getChatId(), messages.get(0).getChatId());
        assertEquals(tamtaId, messages.get(0).getFrom());
        assertEquals(tamtaLekvaMessage.getText(), messages.get(0).getText());

        List<Message> messages2 = chatService.searchMessages(chatLekvaTamta.getChatId(), "ar");
        assertEquals(2, messages2.size());
        assertEquals(chatLekvaTamta.getChatId(), messages2.get(1).getChatId());
        assertEquals(lekvaId, messages2.get(1).getFrom());
        assertEquals(LekvaTamtaMessage.getText(), messages2.get(1).getText());

        List<Message> messages3 = chatService.searchMessages(chatLekvaTamta.getChatId(), "dadebiti");
        assertEquals(0, messages3.size());

        List<Message> messages4 = chatService.searchMessages(chatLekvaTamta.getChatId(), ";)");
        assertEquals(1, messages4.size());
        assertEquals(chatLekvaTamta.getChatId(), messages4.get(0).getChatId());
        assertEquals(lekvaId, messages4.get(0).getFrom());
        assertEquals(LekvaTamtaMessage.getText(), messages4.get(0).getText());
    }

}