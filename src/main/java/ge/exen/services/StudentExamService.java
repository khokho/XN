package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.StudentExamRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentExamService implements IStudentExamService{
    @Autowired
    ExamDao examDao;

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StudentExamDAO studentExamDAO;

    @Override
    public boolean assignStudentToExam(StudentExamRegisterDTO registerDTO){
        //check if the admin is assigning student to the exam
        if(!userService.getCurrentUser().getStatus().equals(User.ADMIN)) return false;

        //check if exam exists
        Exam exam =  examDao.get(registerDTO.getExamId());
        if(exam == null) return false;

        //check if user with this mail exists
        if(!userService.checkMailExists(registerDTO.getStudentMail())) return false;

        long studId = userDAO.getUserByMail(registerDTO.getStudentMail()).getId();
        //check if user with this mail is a student
        if(!userDAO.getStatusByUserId(studId).equals(User.STUDENT)) return false;

        StudentExam studentExam = new StudentExam();
        studentExam.setStudentId(studId);
        studentExam.setExamId(registerDTO.getExamId());
        studentExam.setCompIndex(registerDTO.getCompIndex());
        studentExam.setVariant(registerDTO.getVariant());

        if(!studentExamDAO.create(studentExam)) return false;
        System.out.println(studentExam.toString());

        return true;
    }
}
