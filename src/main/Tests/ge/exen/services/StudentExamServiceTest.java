package ge.exen.services;

import ge.exen.models.Exam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
@Transactional()
public class StudentExamServiceTest {
    @Test
    public void testGetUserById() {
        //Exam exam = new
    }
}
