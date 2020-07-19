package ge.exen.services;

import ge.exen.DAO.*;
import ge.exen.dto.ExamDTO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.ExamMaterial;
import ge.exen.models.StudentExam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })
@Transactional()
public class ExamServiceTest {
    @Autowired
    IExamService testObject;
    @Autowired
    private IUserService userService;
    @Autowired
    ExamDao examDao;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ExamMaterialDao examMaterialDao;
    @Autowired
    StudentExamDAO studentExamDAO;

    @Value("classpath:tests/foo.txt")
    Resource fooFile;

    @Test
    @DirtiesContext
    public void testExamService(){
        ExamDTO dto = new ExamDTO();
        dto.setStartDate("2000/01/01 15:30");
        dto.setFullName("foo");
        dto.setHours(1);
        dto.setMinutes(30);
        dto.setVariants(3);
        long id = testObject.process(dto);
        assertTrue(id > 0);

        Exam got = examDao.get(id);

        assertNotEquals(null, got);
        assertEquals(got.getDurationInMinutes(), (Integer)90);
        assertEquals(got.getFullName(), "foo");
        assertEquals(got.getStartDate(), "2000/01/01 15:30");
        assertEquals(got.getVariants(), (Integer)3);
    }


    @Test
    @DirtiesContext
    public void testFileStoring(){
        long id = 1;



        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        Map<String, MultipartFile> mp = new HashMap<>();
        mp.put("2", result);

        testObject.setFiles(mp, id);
        ExamMaterial got = examMaterialDao.get(id, 2);

        assertNotNull(got);
        assertEquals(2, got.getVar());
        assertEquals(id, got.getExamId());
        assertEquals(ExamMaterialService.directory.replace("%id%", "" + id) + "/foo.txt", got.getMaterialLink());
    }

    @Test
    @DirtiesContext
    public void testGetCurrentExam() {
        UserRegisterDTO tedMosby = new UserRegisterDTO();
        tedMosby.setEmail("selka18@freeuni.edu.ge");
        tedMosby.setName("ted");
        tedMosby.setLastName("mosby");
        tedMosby.setStatus("student");
        tedMosby.setPassword("IWantYouBackRobin");
       assertTrue(userService.registerNewUser(tedMosby));
        UserLoginDTO misterX = new UserLoginDTO();
        misterX.setEmail("selka18@freeuni.edu.ge");
        misterX.setPassword("IWantYouBackRobin");
        userService.login(misterX);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        String withoutHours = currentTime.substring(0,currentTime.indexOf(" "));
        String onlyHours = currentTime.substring(currentTime.indexOf(" ")+1);
        String newOnlyHours = onlyHours.charAt(0)+"";
        if(onlyHours.charAt(1) != '0') {
            newOnlyHours += onlyHours.charAt(1)-'0' -1+"" + onlyHours.substring(2);
        }

        String startDate = withoutHours + " " + newOnlyHours;
        Exam testEx = new Exam( "foo Exam",
                startDate, 120,
                6);
        long status =examDao.create(testEx);
        assertEquals(status,SQLExamDao.OK);
        StudentExam ex = new StudentExam();
        ex.setExamId(testEx.getID());
        ex.setStudentId(userService.getCurrentUser().getId());
        ex.setCompIndex(23);
        ex.setVariant(6);
        assertEquals(studentExamDAO.create(ex),0);
      //  System.out.println(userService.getCurrentUser().getEmail());
        System.out.println(testEx.getID());
        assertTrue(studentExamDAO.get(userService.getCurrentUser().getId(),ex.getExamId()) != null);
        StudentExam ans = testObject.getExamForCurrentUser();
        assertFalse(ans == null);
        assertEquals(ex,ans);


    }

    @Test
    @DirtiesContext
    public void testGetExamsForHighStatus() {

    }
    @Test
    @DirtiesContext
    public void testGetAllCurrentExam(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        String withoutHours = currentTime.substring(0,currentTime.indexOf(" "));
        String onlyHours = currentTime.substring(currentTime.indexOf(" ")+1);
        String newOnlyHours = onlyHours.charAt(0)+"";
        //test with 1 hour
        if(onlyHours.charAt(1) != '0') {
           newOnlyHours += onlyHours.charAt(1)-'0' -1+"" + onlyHours.substring(2);
        }
        //please dont test it on 00: .. 10: .. or 20:.. just please
        String startDate = withoutHours + " " + newOnlyHours;
        Exam testEx = new Exam( "foo Exam",
                startDate, 120,
                6);
        long status = examDao.create(testEx);
        assertEquals(SQLExamDao.OK, status);
        List<Exam> exams = testObject.getAllCurrentExams();
        assertEquals(exams.size(),1);
        Exam testEx1 = new Exam( "foo Exam1",
                startDate,
                13,
                5);
        long status1 = examDao.create(testEx1);
        assertEquals(SQLExamDao.OK, status1);
        exams = testObject.getAllCurrentExams();  //still 1
        assertEquals(exams.size(),1);

        Exam testEx2 = new Exam( "foo Exam2",
                startDate,
                60,
                7);
        long status2 = examDao.create(testEx2);
        exams = testObject.getAllCurrentExams();  //now2
        assertEquals(exams.size(),2);

        withoutHours = withoutHours.substring(0,withoutHours.lastIndexOf("/")) + "/" +
                (Integer.parseInt(withoutHours.substring(withoutHours.lastIndexOf("/")+1 ))-1) + "";
        startDate = withoutHours + onlyHours;
        Exam testEx3 = new Exam( "foo Exam2",
                startDate,
                Integer.MAX_VALUE,
                7);
        long status3 = examDao.create(testEx3);
        assertEquals(SQLExamDao.OK,status3);
        exams = testObject.getAllCurrentExams();  //now 3
        assertEquals(exams.size(),3);
        Exam testEx4 = new Exam( "foo Exam2",
                startDate,
                10,
                7);
        long status4 = examDao.create(testEx4);
        assertEquals(SQLExamDao.OK,status4);
        exams = testObject.getAllCurrentExams();  //still 3
        assertEquals(exams.size(),3);

    }

/*
    @Test
    @DirtiesContext
    @Transactional
    public void lolTestFileStoring(){
        ExamDTO dto = new ExamDTO();
        dto.setStartDate("2000/01/01 15:30");
        dto.setFullName("foo");
        dto.setHours(1);
        dto.setMinutes(30);
        dto.setVariants(3);
        long id = testObject.process(dto);
        assertTrue(id > 0);



        byte[] content = null;
        try {
            File test = fooFile.getFile();
            content = Files.readAllBytes(test.toPath());
        } catch (final IOException e) {
            fail();
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile("foo.txt",
                "foo.txt", "text/plain", content);
        Map<String, MultipartFile> mp = new HashMap<>();
        mp.put("1", result);

        testObject.setFiles(mp, id);
        ExamMaterial got = examMaterialDao.get(id, 1);

        assertNotEquals(null, got);
        assertEquals(1, got.getVar());
        assertEquals(id, got.getExamId());
        assertEquals(ExamMaterialService.directory.replace("%id%", ""+id) + "/foo.txt", got.getMaterialLink());
    }*/



}