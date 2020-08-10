package ge.exen.services;

import ge.exen.dto.ExamDTO;
import ge.exen.dto.ExamLecturersRegisterDTO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration // this annotation creates sessions and stuff
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
public class ExamLecturerServiceTest {
    @Autowired
    IPostsService postsService;

    @Autowired
    IUserService userService;

    @Autowired
    IExamService examService;

    @Autowired
    IExamLecturerService examLecturerService;

    UserRegisterDTO lekva;
    ExamDTO oop;
    ExamLecturersRegisterDTO oopLekva;

    UserRegisterDTO tamta;
    ExamLecturersRegisterDTO oopTamta;

    private Long OOP_ID = 123456789L;

    @BeforeEach
    @DirtiesContext
    public void setup(){
        UserRegisterDTO admin = new UserRegisterDTO();
        admin.setEmail("alkhok18@freeuni.edu.ge");
        admin.setName("Grinch");
        admin.setLastName("Khokhiashvili");
        admin.setPassword("okidok");
        admin.setStatus("admin");
        userService.registerNewUser(admin);



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

        oopTamta = new ExamLecturersRegisterDTO();
        oopTamta.setExamId(oopId);
        oopTamta.setLecturerMail(tamta.getEmail());

        UserLoginDTO adminLogin = new UserLoginDTO();
        adminLogin.setEmail("alkhok18@freeuni.edu.ge");
        adminLogin.setPassword("okidok");
        userService.login(adminLogin);//login as admin
    }

    @Test
    @DirtiesContext
    public void mockActions() {
        assertTrue(examLecturerService.assignLecturerToExam(oopLekva));

        //non lecturer assignation
        assertFalse(examLecturerService.assignLecturerToExam(oopTamta));


    }

}

