package ge.exen.services;

import ge.exen.dto.StudentExamRegisterDTO;

public interface IStudentExamService {
    /**
     * assigns student to specific exam by registering new StudentExam object
     * in the database (student_id, exam_id, variant, comp_index)
     * @param registerDTO
     * @return true if successfully assigned student to exam, false otherwise
     */
    boolean assignStudentToExam(StudentExamRegisterDTO registerDTO);

}
