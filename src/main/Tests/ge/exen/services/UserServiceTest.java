package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class UserServiceTest {

    @Autowired
    private PasswordEncoder hashService;

    @Autowired
    private UserService underTest;

    @Test
    public void registerNewUser() {
        UserRegisterDTO input = new UserRegisterDTO();
        input.setEmail("alkhok18@freeuni.edu.ge");
        input.setName("Aleksandre");
        input.setLastName("Khokhvardi");
        input.setStatus("user");
        input.setPassword("testpass");
        User newUser = underTest.registerNewUser(input);
        assertNotEquals(-1, newUser.getId() );

        User duplicateUser = underTest.registerNewUser(input);
        assertEquals(-1, duplicateUser.getId() );

        assertTrue(hashService.matches(input.getPassword(), newUser.getPasswordHash()));
    }

}