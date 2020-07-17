package ge.exen.services;

import ge.exen.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration // this annotation creates sessions and stuff
@ContextConfiguration(locations = { "classpath:dispatcher-servlet.xml" })
public class PostServiceTest {

    @Autowired
    IPostsService postsService;

    @Autowired
    IUserService userService;

    @Autowired
    IExamService examService;

    @Autowired
    IExamLecturerService examLecturerService;

    PostWriteDTO lekvasPost;
    UserRegisterDTO lekva;
    ExamDTO oop;
    ExamLecturersRegisterDTO oopLekva;

    PostWriteDTO tamtasPost;
    UserRegisterDTO tamta;


    private Long OOP_ID = 123456789L;

    @BeforeEach
    @DirtiesContext
    public void setup(){
        lekva = new UserRegisterDTO();
        lekva.setEmail("g.lekveishvili@freeuni.edu.ge");
        lekva.setName("Giorgi");
        lekva.setLastName("Lekveishvili");
        lekva.setPassword("topcoder7834");
        lekva.setStatus("lector");
        userService.registerNewUser(lekva);

        tamta = new UserRegisterDTO();
        tamta.setEmail("ttopu18@freeuni.edu.ge");
        tamta.setName("Tamta");
        tamta.setLastName("Topuria");
        tamta.setPassword("PIOpaopioPAO");
        tamta.setStatus("student");
        userService.registerNewUser(tamta);

        oop = new ExamDTO();
        oop.setVariants(1);
        oop.setStartDate("2020/08/03 16:00");
        oop.setFullName("OOP");
        oop.setHours(3);
        oop.setMinutes(0);
        Long oopId = examService.process(oop);

        oopLekva = new ExamLecturersRegisterDTO();
        oopLekva.setExamId(oopId);
        oopLekva.setLecturerMail(lekva.getEmail());
        assertTrue(examLecturerService.assignLecturerToExam(oopLekva));
        examLecturerService.assignLecturerToExam(oopLekva);

        lekvasPost = new PostWriteDTO();
        lekvasPost.setExamId(oopId);
        String text = "Meore amocanis pirobashi mcire xarvezia: weria (...raghaca..)" +
                "unda eweros (...raghaca...)";
        lekvasPost.setText(text);

        tamtasPost = new PostWriteDTO();
        tamtasPost.setExamId(oopId);
        String tamtasText = "modi, ana, morzeze vilaparakot";
        tamtasPost.setText(tamtasText);

    }

    @Test
    @DirtiesContext
    public void testWriteNewPost() {
        assertNotNull(postsService.writeNewPost(lekvasPost));
        //non lecturer trying to write a post
        assertNull(postsService.writeNewPost(tamtasPost));

        tamta.setStatus("lector");
        //lecturer trying to write a post in other lecturer's subject
        assertNull(postsService.writeNewPost(tamtasPost));
    }

}
