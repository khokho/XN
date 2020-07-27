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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration // this annotation creates sessions and stuff
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class PostServiceTest {

    @Autowired
    IPostsService postsService;

    @Autowired
    IUserService userService;

    @Autowired
    IExamService examService;

    @Autowired
    IExamLecturerService examLecturerService;

    @Autowired
    IStudentExamService studentExamService;


    PostWriteDTO lekvasPost;
    UserRegisterDTO lekva;
    ExamDTO oop;
    Long oopId;
    ExamLecturersRegisterDTO oopLekva;

    PostWriteDTO tamtasPost;
    UserRegisterDTO tamta;

    PostWriteDTO lekvasNextPost;

    UserLoginDTO lekvaLogin;
    UserLoginDTO tamtaLogin;
    UserLoginDTO nanaLogin;

    UserRegisterDTO nanaAdmin;

    private Long OOP_ID = 123456789L;

    @BeforeEach
    public void setup(){
        nanaAdmin = new UserRegisterDTO();
        nanaAdmin.setEmail("n.kikacheishvili@freeuni.edu.ge");
        nanaAdmin.setName("Nana");
        nanaAdmin.setLastName("Kikacheishvili");
        nanaAdmin.setPassword("adminYOyoYo");
        nanaAdmin.setStatus("admin");
        //ACTION
        userService.registerNewUser(nanaAdmin);

        nanaLogin = new UserLoginDTO();
        nanaLogin.setEmail(nanaAdmin.getEmail());
        nanaLogin.setPassword(nanaAdmin.getPassword());

        lekva = new UserRegisterDTO();
        lekva.setEmail("g.lekveishvili@freeuni.edu.ge");
        lekva.setName("Giorgi");
        lekva.setLastName("Lekveishvili");
        lekva.setPassword("topcoder7834");
        lekva.setStatus("lector");
        //ACTION
        userService.registerNewUser(lekva);

        oop = new ExamDTO();
        oop.setVariants(1);
        oop.setStartDate("2020/08/03 16:00");
        oop.setFullName("OOP");
        oop.setHours(3);
        oop.setMinutes(0);
        oopId = examService.process(oop);

        oopLekva = new ExamLecturersRegisterDTO();
        oopLekva.setExamId(oopId);
        oopLekva.setLecturerMail(lekva.getEmail());
        //ACTION
        userService.login(nanaLogin);
        examLecturerService.assignLecturerToExam(oopLekva);

        lekvasPost = new PostWriteDTO();
        lekvasPost.setExamId(oopId);
        String text = "Meore amocanis pirobashi mcire xarvezia: weria (...raghaca..)" +
                "unda eweros (...raghaca...)";
        lekvasPost.setText(text);

        tamta = new UserRegisterDTO();
        tamta.setEmail("ttopu18@freeuni.edu.ge");
        tamta.setName("Tamta");
        tamta.setLastName("Topuria");
        tamta.setPassword("PIOpaopioPAO");
        tamta.setStatus("student");
        //ACTION
        userService.registerNewUser(tamta);

        tamtasPost = new PostWriteDTO();
        tamtasPost.setExamId(oopId);
        String tamtasText = "modi, ana, morzeze vilaparakot";
        tamtasPost.setText(tamtasText);

        lekvaLogin = new UserLoginDTO();
        lekvaLogin.setEmail(lekva.getEmail());
        lekvaLogin.setPassword(lekva.getPassword());

        tamtaLogin = new UserLoginDTO();
        tamtaLogin.setEmail(tamta.getEmail());
        tamtaLogin.setPassword(tamta.getPassword());

        userService.login(nanaLogin);
        StudentExamRegisterDTO tamtaOOP = new StudentExamRegisterDTO();
        tamtaOOP.setStudentMail(tamta.getEmail());
        tamtaOOP.setExamId(oopId);
        tamtaOOP.setCompIndex(133L);
        tamtaOOP.setVariant(1L);
        //ACTION
        userService.login(nanaLogin);
        studentExamService.assignStudentToExam(tamtaOOP);
    }

    @Test
    @DirtiesContext
    public void testWriteNewPost() {
        userService.logout();
        userService.login(lekvaLogin);
        assertNotNull(postsService.writeNewPost(lekvasPost));
        System.out.println(userService.getCurrentUser().getEmail());
        System.out.println(postsService.getPostsByUserId());
        userService.logout();

        //non lecturer trying to write a post
        userService.login(tamtaLogin);
        assertNull(postsService.writeNewPost(tamtasPost));

        tamta.setStatus("lector");
        //lecturer trying to write a post in other lecturer's subject
        assertNull(postsService.writeNewPost(tamtasPost));
        System.out.println(userService.getCurrentUser().getEmail());
        System.out.println(postsService.getPostsByUserId());
        userService.logout();
    }

    @Test
    @DirtiesContext
    public void testEditPost(){
        System.out.println(userService.getCurrentUser().getEmail());
        System.out.println(postsService.getPostsByUserId());
    }

    @Test
    @DirtiesContext
    public void testGetPostsByUserIdLecturer(){
        userService.login(lekvaLogin);
        assertNotNull(postsService.writeNewPost(lekvasPost));
        assertEquals(1, postsService.getPostsByUserId().size());
        assertEquals(userService.getCurrentUser().getId(), postsService.getPostsByUserId().get(0).getFromId());
        assertEquals(oopId, postsService.getPostsByUserId().get(0).getExamId());
        assertEquals(lekvasPost.getText(), postsService.getPostsByUserId().get(0).getText());

        lekvasNextPost = new PostWriteDTO();
        lekvasNextPost.setText("chemi meore posti - lekva");
        lekvasNextPost.setExamId(oopId);
        postsService.writeNewPost(lekvasNextPost);

        assertEquals(2, postsService.getPostsByUserId().size());
        assertEquals(lekvasNextPost.getText(), postsService.getPostsByUserId().get(1).getText());
        assertEquals(oopId, postsService.getPostsByUserId().get(1).getExamId());

        userService.logout();
    }

    //@Test uncomment when examService is written
    @DirtiesContext
    public void testGetPostsByUserIdStudent(){
        userService.login(nanaLogin);
        //when admin is logged in there are no posts to show
        assertNull(postsService.getPostsByUserId());
        userService.logout();

        //TODO make oop current exam, otherwise there will be no posts shown
        userService.login(tamtaLogin);
        //only lekvas first post
        assertEquals(1, postsService.getPostsByUserId().size());
        assertEquals(lekvasPost.getText(), postsService.getPostsByUserId().get(0).getText());
    }

}
