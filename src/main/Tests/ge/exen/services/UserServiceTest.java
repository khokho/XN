package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.dto.UserUpdateDTO;
import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration // this annotation creates sessions and stuff
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class UserServiceTest {

    @Autowired
    private PasswordEncoder hashService;

    @Autowired
    private IUserService underTest;

    UserRegisterDTO baqar;
    UserRegisterDTO zaal;

    @BeforeEach
    public void setup(){
        baqar = new UserRegisterDTO();
        baqar.setEmail("bgame19@freeuni.edu.ge");
        baqar.setName("Baqar");
        baqar.setLastName("Gamezardashvili");
        baqar.setStatus("user");
        baqar.setPassword("gambit");

        zaal = new UserRegisterDTO();
        zaal.setEmail("z.chkheidze@freeuni.edu.ge");
        zaal.setName("Zaal");
        zaal.setLastName("Chkheidze");
        zaal.setPassword("zazuliko");
        zaal.setStatus("lector");
    }

    @Test
    @DirtiesContext
    public void mockActions() {

        assertTrue(underTest.registerNewUser(baqar));
        assertTrue(hashService.matches(baqar.getPassword(), underTest.getCurrentUser().getPasswordHash()));
        underTest.logout();

        assertFalse(underTest.registerNewUser(baqar));
        underTest.logout();

        assertTrue(underTest.registerNewUser(zaal));
        assertTrue(hashService.matches(zaal.getPassword(), underTest.getCurrentUser().getPasswordHash()));
        underTest.logout();

        UserLoginDTO letBaqarIn = new UserLoginDTO();
        letBaqarIn.setEmail("bgame19@freeuni.edu.ge");
        letBaqarIn.setPassword("khokhobandzia");
        assertFalse(underTest.login(letBaqarIn));

        letBaqarIn.setPassword("gambit");
        assertTrue(underTest.login(letBaqarIn));
        assertEquals(baqar.getName(), underTest.getCurrentUser().getName());
        underTest.logout();
        assertNull(underTest.getCurrentUser());
    }

    @Test
    @DirtiesContext
    public void badLogin() {
        UserLoginDTO letBaqarIn = new UserLoginDTO();
        letBaqarIn.setEmail("bgame19@freeuni.edu.ge");
        letBaqarIn.setPassword("gambit");

        assertFalse(underTest.login(letBaqarIn));
    }

    @Test
    @DirtiesContext
    public void updateTest(){
        assertTrue(underTest.registerNewUser(baqar));
        UserUpdateDTO update = new UserUpdateDTO();
        update.setOldEmail("bgame19@freeuni.edu.ge");
        update.setOldPassword("gambit");

        update.setPassword("parolparol");
        update.setName("baqarich");
        assertTrue(underTest.updateUser(update));
        underTest.logout();
        UserLoginDTO letBaqarIn = new UserLoginDTO();

        letBaqarIn.setEmail("bgame19@freeuni.edu.ge");
        letBaqarIn.setPassword("gambit");
        assertFalse(underTest.login(letBaqarIn));
        letBaqarIn.setPassword("parolparol");
        assertTrue(underTest.login(letBaqarIn));
        assertEquals(update.getName(), underTest.getCurrentUser().getName());
    }




}