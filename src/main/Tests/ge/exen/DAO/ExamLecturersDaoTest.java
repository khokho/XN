package ge.exen.DAO;

import ge.exen.models.ExamLecturers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;

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

@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class ExamLecturersDaoTest {
    @Autowired
    ExamLecturersDAO dao;

    @Test
    @DirtiesContext
    public void testCreate() {
        ExamLecturers examLecturers = new ExamLecturers(1, 1);
        assertTrue(dao.create(examLecturers));
        ExamLecturers examLecturers1 = new ExamLecturers(1, 2);
        assertTrue(dao.create(examLecturers1));
        assertFalse((dao.create(new ExamLecturers(0,0))));
    }

    @Test
    @DirtiesContext
    public void testCheck() {
        ExamLecturers examLecturers = new ExamLecturers(1, 1);
        assertTrue(dao.create(examLecturers));
        ExamLecturers examLecturers1 = new ExamLecturers(1, 2);
        assertTrue(dao.create(examLecturers1));
        ExamLecturers examLecturers2 = new ExamLecturers(0, 2);
        assertTrue(dao.check(examLecturers) && dao.check(examLecturers1));
        assertFalse(dao.check(examLecturers2));
    }


}
