package ge.exen.services;

import ge.exen.dto.ExamDTO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.Chat;
import ge.exen.models.ExamLecturers;
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

    long lekvaId;
    UserRegisterDTO lekva;
    UserRegisterDTO tamta;
    UserLoginDTO tamtaLogin;

    @BeforeEach
    @DirtiesContext
    public void setup(){
        lekva = new UserRegisterDTO();
        lekva.setEmail("g.lekveishvili@freeuni.edu.ge");
        lekva.setName("Giorgi");
        lekva.setLastName("Lekveishvili");
        lekva.setPassword("topcoder7834");
        lekva.setStatus("lector");
        //ACTION
        userService.registerNewUser(lekva);
        lekvaId = userService.getCurrentUser().getId();

        tamta = new UserRegisterDTO();
        tamta.setEmail("ttopu18@freeuni.edu.ge");
        tamta.setName("Tamta");
        tamta.setLastName("Topuria");
        tamta.setPassword("PIOpaopioPAO");
        tamta.setStatus("student");
        //ACTION
        userService.registerNewUser(tamta);

        tamtaLogin = new UserLoginDTO();
        tamtaLogin.setEmail(tamta.getEmail());
        tamtaLogin.setPassword(tamta.getPassword());


    }


    @Test
    void getMyChats() {

    }

    @Test
    void getMessages() {
    }

    @Test
    void searchMessages() {
    }

    @Test
    void startChat() {
        userService.login(tamtaLogin);
        Chat chatLekvaTamta = chatService.startChat(lekvaId);
        assertNotNull(chatLekvaTamta);
        //chatLekvaTamta.get
    }

    @Test
    void sendMessage(){

    }
}