package ge.exen.DAO;

import ge.exen.models.Exam;
import ge.exen.models.ExamMaterial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class ExamMaterialDAOTest {
    @Autowired
    ExamMaterialDao dao;



    @Test
    @DirtiesContext
    public void testMaterials(){
        ExamMaterial testMat = new ExamMaterial("classpath:tests/foo.txt",
                                                2,
                                                1);
        int status = dao.create(testMat);
        assertEquals(ExamMaterialDao.OK, status);

        ExamMaterial got = dao.get(1, 2);
        assertNotNull(got);
        assertEquals("classpath:tests/foo.txt", got.getMaterialLink());
        assertEquals(2, got.getVar());
        assertEquals(1, got.getExamId());
    }

    @Test
    public void testFaulty() {
        ExamMaterial got = dao.get(100000, 100000);
        assertNull(got);

    }
}
