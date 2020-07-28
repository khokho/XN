package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.ExamLecturersRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.ExamLecturers;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamLecturerService implements IExamLecturerService{
    @Autowired
    ExamDao examDao;

    @Autowired
    IUserService userService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ExamLecturersDAO examLecturersDAO;

    @Override
    public boolean assignLecturerToExam(ExamLecturersRegisterDTO registerDTO){
        //check if the admin is assigning lecturer to the exam
        if(!userService.getCurrentUser().getStatus().equals(User.ADMIN)) return false;

        //check if exam exists
        Exam exam =  examDao.get(registerDTO.getExamId());
        if(exam == null) return false;

        long examId = exam.getID();
        //check if user with this mail exists
        if(!userService.checkMailExists(registerDTO.getLecturerMail())) return false;

        long lectId = userDAO.getUserByMail(registerDTO.getLecturerMail()).getId();
        //check if user with this mail is a lecturer
        if(!userDAO.getStatusByUserId(lectId).equals(User.LECTURER)) return false;

        ExamLecturers examLecturer = new ExamLecturers(examId, lectId);
        if(!examLecturersDAO.create(examLecturer)) return false;
        System.out.println(examLecturer.toString());
        return true;
    }
}
