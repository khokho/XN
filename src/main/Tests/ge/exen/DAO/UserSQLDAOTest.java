package ge.exen.DAO;


import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class UserSQLDAOTest {

    @Autowired
    private UserSQLDAO userSQLDAO;

    User sample = null;

    @BeforeEach
    public void setUp(){
        sample = new User();
        sample.setName("Baqar");
        sample.setLastName("Gamezardashvili");
        sample.setEmail("bgame19@freeuni.edu.ge");
        sample.setPasswordHash("vitom heshi var");
        sample.setStatus("student");
    }

    @Test
    @DirtiesContext
    public void createNormal() {

        userSQLDAO.create(sample);
        assertTrue(sample.getId() >= 0);

        userSQLDAO.create(sample);
        assertEquals(sample.getId(), -1);

        sample.setEmail("bgame20@freeuni.edu.ge");
        userSQLDAO.create(sample);
        assertTrue(sample.getId() >= 0);
    }


    @Test
    public void createNullz1() {
        sample.setPasswordHash(null);
        userSQLDAO.create(sample);
        assertEquals(sample.getId(), -1);
    }

    @Test
    public void createNullz2() {
        sample.setEmail(null);
        userSQLDAO.create(sample);
        assertEquals(sample.getId(), -1);
    }

    @Test
    public void createNullz3() {
        sample.setStatus(null);
        userSQLDAO.create(sample);
        assertEquals(sample.getId(), -1);
    }


    @Test
    @DirtiesContext
    public void getUser() {
        userSQLDAO.create(sample);
        System.out.println(sample.getId() + " name: " + sample.getName());
        assertEquals(userSQLDAO.getUser(sample.getId()).getEmail(), sample.getEmail());
        assertNull(userSQLDAO.getUser(sample.getId()+1));
    }

    @Test
    @DirtiesContext
    public void getUserByMail() {
        userSQLDAO.create(sample);
        sample.setEmail("1@freeuni.edu.ge");
        userSQLDAO.create(sample);
        sample.setEmail("b@freeuni.edu.ge");
        userSQLDAO.create(sample);
        assertEquals(userSQLDAO.getUserByMail(sample.getEmail()).getId(), sample.getId());
        assertNull(userSQLDAO.getUserByMail("khokho@feruni.du.ge"));
        assertNull(userSQLDAO.getUserByMail(null));
    }

    @Test
    @DirtiesContext
    public void getUsersByStatus() {
        sample.setStatus("admin");
        sample.setName("admin1");
        sample.setEmail("admin1@freeuni.edu.ge");
        userSQLDAO.create(sample);
        sample.setName("admin3");
        sample.setEmail("admin3@freeuni.edu.ge");
        userSQLDAO.create(sample);
        sample.setName("admin2");
        sample.setEmail("admin2@freeuni.edu.ge");
        userSQLDAO.create(sample);

        sample.setStatus("student");
        sample.setName("user12");
        sample.setEmail("user12@freeuni.edu.ge");
        userSQLDAO.create(sample);

        assertEquals(3,userSQLDAO.getUsersByStatus("admin").size());
        System.out.println((userSQLDAO.getUsersByStatus("student")).toString());
        assertEquals(3,userSQLDAO.getUsersByStatus("student").size());//expects 2 in table



    }

    @Test
    @DirtiesContext
    void updateRowById() {
        userSQLDAO.create(sample);
        assertEquals("student", userSQLDAO.getUser(sample.getId()).getStatus());
        assertEquals("student", userSQLDAO.getUserByMail(sample.getEmail()).getStatus());
        assertEquals("student", userSQLDAO.getStatusByUserId(sample.getId()));
        sample.setStatus("admin");
        userSQLDAO.updateRowById(sample);
        assertNotEquals(-1, sample.getId());
        assertEquals("admin", userSQLDAO.getUser(sample.getId()).getStatus());
        assertEquals("admin", userSQLDAO.getUserByMail(sample.getEmail()).getStatus());
        assertEquals("admin", userSQLDAO.getStatusByUserId(sample.getId()));
        assertTrue(userSQLDAO.removeUserById(sample.getId()));
        assertNull(userSQLDAO.getUserByMail(sample.getEmail()));
        assertNull(userSQLDAO.getStatusByUserId(sample.getId()));


    }

    @Test
    @DirtiesContext
    void removeUserById() {

        userSQLDAO.create(sample);
        assertNotNull(userSQLDAO.getUser(sample.getId()));

        assertTrue(userSQLDAO.removeUserById(sample.getId()));
        assertNull(userSQLDAO.getUser(sample.getId()));

        assertFalse(userSQLDAO.removeUserById(sample.getId()));
        assertNull(userSQLDAO.getUser(sample.getId()));

    }

}
