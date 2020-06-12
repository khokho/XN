package ge.exen.DAO;

import ge.exen.models.Exam;
import ge.exen.models.ExamMaterial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.sql.SQLException;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class ExamMaterialDAOTest {
    @Autowired
    ExamMaterialDao dao;
    @Autowired
    ExamDao exdao;


    @Test
    public void testMaterials(){
        Exam testEx = new Exam( "foo Exam",
                "2015/01/02 17:22",
                12,
                6);
        long exStatus = exdao.create(testEx);
        assertEquals(SQLExamDao.OK, exStatus);


        ExamMaterial testMat = new ExamMaterial("./foo.txt",
                                                2,
                                                testEx.getID());
        int status = dao.create(testMat);
        assertEquals(ExamMaterialDao.OK, status);

        ExamMaterial got = dao.get(testEx.getID(), 2);
        assertNotNull(got);
        assertEquals("./foo.txt", got.getMaterialLink());
        assertEquals(2, got.getVar());
        assertEquals(testEx.getID(), got.getExamId());
    }

    @Test
    public void testFaulty() throws SQLException {
        ExamMaterial got = dao.get(100000, 100000);
        assertNull(got);

    }
}
