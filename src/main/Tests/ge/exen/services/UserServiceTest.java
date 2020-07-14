package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class UserServiceTest {

    @Autowired
    private PasswordEncoder hashService;

    @Autowired
    private UserService underTest;

    @Test
    @DirtiesContext
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