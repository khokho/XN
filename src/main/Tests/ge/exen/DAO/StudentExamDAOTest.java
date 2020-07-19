package ge.exen.DAO;

import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
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
@ContextConfiguration(locations = {"classpath:testing-setup.xml"})
public class StudentExamDAOTest {
    @Autowired
    private StudentExamDAO studentExamDAO;

    @Autowired
    private ExamDao examDao;

    @Autowired
    private UserDAO userDAO;

    private StudentExam sample;

    @BeforeEach
    public void setUp() {
        sample = new StudentExam();
        sample.setExamId(1);
        sample.setStudentId(2);
        sample.setVariant(2);
        sample.setCompIndex(4);
    }

    @Test
    @DirtiesContext
    public void testCreateValid() {
        int added = studentExamDAO.create(sample);
        assertEquals(0, added);
    }

    @Test
    @DirtiesContext
    public void testCreateSameStudentAndExam() {
        studentExamDAO.create(sample);
        sample.setVariant(1);
        int added = studentExamDAO.create(sample);
        assertEquals(-1, added);
    }

    @Test
    @DirtiesContext
    public void testCreateSameExamAndComputer() {
        studentExamDAO.create(sample);
        sample.setStudentId(1);
        int added = studentExamDAO.create(sample);
        assertEquals(-1, added);
    }

    @Test
    @DirtiesContext
    public void testCreateNullParameters() {
        StudentExam nullStudent = new StudentExam();
        assertEquals(-1, studentExamDAO.create(nullStudent));

        nullStudent.setExamId(1);
        nullStudent.setVariant(2);
        nullStudent.setCompIndex(4);
        assertEquals(-1, studentExamDAO.create(nullStudent));

        StudentExam nullExam = new StudentExam();
        nullExam.setStudentId(2);
        nullExam.setVariant(2);
        nullExam.setCompIndex(4);
        assertEquals(-1, studentExamDAO.create(nullExam));
    }

    @Test
    @DirtiesContext
    public void testCreateNotValidParameters() {
        sample.setVariant(-4);
        assertEquals(-1, studentExamDAO.create(sample));

        sample.setVariant(2);
        sample.setCompIndex(-5);
        assertEquals(-1, studentExamDAO.create(sample));

        sample.setStudentId(1444);
        sample.setCompIndex(3);
        assertEquals(-1, studentExamDAO.create(sample));

        sample.setStudentId(1);
        sample.setExamId(2142);
        assertEquals(-1, studentExamDAO.create(sample));
    }

    @Test
    @DirtiesContext
    public void testGetValid() {
        studentExamDAO.create(sample);
        StudentExam res = studentExamDAO.get(sample.getStudentId(), sample.getExamId());
        assertEquals(sample.getStudentId(), res.getStudentId());
        assertEquals(sample.getExamId(), res.getExamId());
        assertEquals(sample.getVariant(), res.getVariant());
        assertEquals(sample.getCompIndex(), res.getCompIndex());
    }

    @Test
    @DirtiesContext
    public void testGetNotValid() {
        studentExamDAO.create(sample);
        sample.setStudentId(1222);
        StudentExam res = studentExamDAO.get(sample.getStudentId(), sample.getExamId());
        assertNull(res);
    }

    @Test
    @DirtiesContext
    public void testGetByComputerValid() {
        studentExamDAO.create(sample);
        StudentExam res = studentExamDAO.getByComputer(sample.getExamId(), sample.getCompIndex());
        assertEquals(sample.getStudentId(), res.getStudentId());
        assertEquals(sample.getExamId(), res.getExamId());
        assertEquals(sample.getVariant(), res.getVariant());
        assertEquals(sample.getCompIndex(), res.getCompIndex());
    }

    @Test
    @DirtiesContext
    public void testGetByComputerNotValid() {
        studentExamDAO.create(sample);
        sample.setCompIndex(78);
        StudentExam res = studentExamDAO.getByComputer(sample.getExamId(), sample.getCompIndex());
        assertNull(res);

        sample.setCompIndex(4);
        sample.setExamId(676);
        res = studentExamDAO.getByComputer(sample.getExamId(), sample.getCompIndex());
        assertNull(res);
    }

    @Test
    @DirtiesContext
    public void testGetByStudentValid() {
        studentExamDAO.create(sample);
        long studentId = sample.getStudentId();

        Exam newExam = new Exam("kalkulusi",
                "2020/07/07 10:00",
                120,
                4);
        examDao.create(newExam);

        StudentExam sample2 = new StudentExam(studentId, newExam.getID(), 1, 7);
        studentExamDAO.create(sample2);

        List<StudentExam> res = studentExamDAO.getByStudent(studentId);
        assertEquals(2, res.size());
        boolean sample2Returned = false;
        boolean sampleReturned = false;

        for(StudentExam s : res){
            if(s.getExamId() == sample2.getExamId()) sample2Returned = true;
            if(s.getExamId() == sample.getExamId()) sampleReturned = true;
        }

        assertTrue(sample2Returned && sampleReturned);
    }

    @Test
    @DirtiesContext
    public void testGetByStudentNotValid() {
        studentExamDAO.create(sample);

        List<StudentExam> res = studentExamDAO.getByStudent(76);
        assertEquals(0, res.size());
    }

    @Test
    @DirtiesContext
    public void testGetByExamValid() {
        studentExamDAO.create(sample);
        long studentId = sample.getStudentId();

        Exam newExam = new Exam("kalkulusi",
                "2020/07/07 10:00",
                120,
                4);
        examDao.create(newExam);

        StudentExam sample2 = new StudentExam(studentId, newExam.getID(), 1, 7);
        studentExamDAO.create(sample2);

        List<StudentExam> res = studentExamDAO.getByExam(sample2.getExamId());
        assertEquals(1, res.size());
        assertEquals(sample2.getExamId(), res.get(0).getExamId());
        assertEquals(studentId, res.get(0).getStudentId());
    }

    @Test
    @DirtiesContext
    public void testGetByExamNotValid() {
        studentExamDAO.create(sample);

        List<StudentExam> res = studentExamDAO.getByExam(24);
        assertEquals(0, res.size());
    }

    @Test
    @DirtiesContext
    public void testGetByVariantValid() {
        studentExamDAO.create(sample);
        long studentId = sample.getStudentId();

        Exam newExam = new Exam("kalkulusi",
                "2020/07/07 10:00",
                120,
                4);
        examDao.create(newExam);
        long examId = newExam.getID();

        sample.setExamId(examId);
        studentExamDAO.create(sample);
        long variant = 3;
        StudentExam sample2 = new StudentExam(1, examId, variant, 7);
        studentExamDAO.create(sample2);

        User student3 = new User();
        student3.setEmail("adavi18@freeuni.edu.ge");
        student3.setName("Ana");
        student3.setLastName("Davitashvili");
        student3.setPasswordHash("passwordhash");
        student3.setStatus("student");
        userDAO.create(student3);

        StudentExam sample3 = new StudentExam(student3.getId(), examId, variant, 9);
        studentExamDAO.create(sample3);

        List<StudentExam> res = studentExamDAO.getByVariant(examId, variant);


        assertEquals(2, res.size());
        boolean sample2Returned = false;
        boolean sample3Returned = false;

        for(StudentExam s : res){
            if(s.getExamId() == sample2.getExamId()) sample2Returned = true;
            if(s.getExamId() == sample3.getExamId()) sample3Returned = true;
        }

        assertTrue(sample2Returned && sample3Returned);
    }

    @Test
    @DirtiesContext
    public void testGetByVariantNotValid() {
        studentExamDAO.create(sample);

        List<StudentExam> res = studentExamDAO.getByVariant(1, 24);
        assertEquals(0, res.size());

        res = studentExamDAO.getByVariant(23, 2);
        assertEquals(0, res.size());
    }

    @Test
    @DirtiesContext
    public void testChangeComputerValid() {
        studentExamDAO.create(sample);

        int newComp = 34;
        int changed = studentExamDAO.changeComputer(sample.getStudentId(), sample.getExamId(), newComp);
        assertEquals(0, changed);

        assertNull(studentExamDAO.getByComputer(sample.getExamId(), sample.getCompIndex()));

        StudentExam changedStudExam = studentExamDAO.get(sample.getStudentId(), sample.getExamId());
        assertEquals(newComp, changedStudExam.getCompIndex());

    }

    @Test
    @DirtiesContext
    public void testChangeComputerAlreadyTaken() {
        studentExamDAO.create(sample);
        long compIndex = sample.getCompIndex();

        long currCompIndex = 7;
        StudentExam sample2 = new StudentExam(1, sample.getExamId(), 3, currCompIndex);
        studentExamDAO.create(sample2);

        int changed = studentExamDAO.changeComputer(sample2.getStudentId(), sample2.getExamId(), compIndex);
        assertEquals(-1, changed);
        assertEquals(currCompIndex, sample2.getCompIndex());
    }

    @Test
    @DirtiesContext
    public void testChangeComputerNotValidStudentExam() {
        //sample is not created
        long newComp = 7;
        int changed = studentExamDAO.changeComputer(sample.getStudentId(), sample.getExamId(), newComp);
        assertEquals(-1, changed);
    }

}
