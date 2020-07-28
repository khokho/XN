package ge.exen.services;

import ge.exen.dto.ExamLecturersRegisterDTO;

public interface IExamLecturerService {
    /**
     * assigns lecturer to the exam by
     * registering given pair of lecturer and an exam in database.
     *
     * @param registerDTO
     * @return true of registered successfully, otherwise false
     */
    boolean assignLecturerToExam(ExamLecturersRegisterDTO registerDTO);
}
