package ge.exen.services;

import ge.exen.dto.StudentExamRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.User;

import java.util.List;

public interface IStudentExamService {
    /**
     * assigns student to specific exam by registering new StudentExam object
     * in the database (student_id, exam_id, variant, comp_index)
     * @param registerDTO
     * @return true if successfully assigned student to exam, false otherwise
     */
    boolean assignStudentToExam(StudentExamRegisterDTO registerDTO);

    /**
     * takes exam id and returns users related to this exam
     * @param examId
     * @return user lists which are attending this exams
     */
    List<User> getUsersByExamId(long examId);

}
