package ge.exen.DAO;

import ge.exen.models.StudentExam;
import ge.exen.models.Upload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class UserUploadDAOTest {
    
    @Autowired
    private UserUploadDAO dao;
    
    @Test
    @DirtiesContext
    public void testStore(){
        Upload up = new Upload();
        up.setFileLink("src/main/resources/tests/foo.txt");
        up.setTime(new Timestamp(new Date().getTime()));
        up.setVarId(1);
        up.setExamId(1);
        up.setFromId(1);

        dao.storeupload(up);
        StudentExam se = new StudentExam();
        se.setExamId(1);
        se.setStudentId(1);
        se.setVariant(1);
        assertTrue(dao.getForUser(se).size()>0);

    }
}
