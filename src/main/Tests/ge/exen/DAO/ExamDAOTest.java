package ge.exen.DAO;

import ge.exen.models.Exam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class ExamDAOTest {
    @Autowired
    ExamDao dao;

    @Test
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
}
