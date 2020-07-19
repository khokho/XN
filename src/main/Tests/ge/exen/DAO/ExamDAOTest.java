package ge.exen.DAO;

import ge.exen.models.Exam;
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
public class ExamDAOTest {
    @Autowired
    ExamDao dao;

    @Test
    @DirtiesContext
    public void testExamDao(){

        Exam testEx = new Exam( "foo Exam",
                                "2015/01/02 17:22",
                                12,
                                6);
        long status = dao.create(testEx);
        assertEquals(SQLExamDao.OK, status);

        Exam got = dao.get(testEx.getID());

        assertNotEquals(null, got);

        assertEquals(6, (int)got.getVariants());
        assertEquals(12, (int)got.getDurationInMinutes());
        assertEquals("2015/01/02 17:22", got.getStartDate());
        assertEquals("foo Exam", got.getFullName());

        got = dao.get(1000000);
        assertNull(got);

        testEx.setStartDate("!!!!");
        status = dao.create(testEx);
        assertNotEquals(SQLExamDao.OK, status);
    }
    @Test
    @DirtiesContext
    public void testGetAll() {
        Exam testEx = new Exam( "foo Exam",
                "2015/01/02 17:22",
                12,
                6);
        long status = dao.create(testEx);
        assertEquals(SQLExamDao.OK, status);
        Exam testEx1 = new Exam( "foo Exam1",
                "2015/01/02 17:23",
                13,
                5);
        long status1 = dao.create(testEx1);
        assertEquals(SQLExamDao.OK, status);
        Exam testEx2 = new Exam( "foo Exam2",
                "2015/01/02 17:21",
                16,
                7);
        long status2 = dao.create(testEx2);
        assertEquals(SQLExamDao.OK, status);
        List<Exam> exams = dao.getAll();
        assertEquals(4,exams.size());
        assertTrue(exams.contains(testEx));
        assertTrue(exams.contains(testEx1));
        assertTrue(exams.contains(testEx2));
    }
}
