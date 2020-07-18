package ge.exen.DAO;

import ge.exen.models.StudentExam;

import java.util.List;

public interface StudentExamDAO {
    /**
     * Adds given studentExam(student's data on the exam) in the DB.
     *
     * @param studentExam data to be added in the DB.
     */
    boolean create(StudentExam studentExam);

    /**
     * Returns a StudentExam of given student on the given exam.
     * Returns null if corresponding StudentExam does not exist.
     *
     * @param studentId long representing the student's ID
     * @param examId    long representing the exam's ID
     * @return StudentExam
     */
    StudentExam get(long studentId, long examId);

    /**
     * Returns StudentExam on the given exam and computer.
     * Returns null if corresponding StudentExam does not exist.
     *
     * @param examId    long representing the exam's ID
     * @param compIndex long representing the computer's index
     * @return StudentExam
     */
    StudentExam getByComputer(long examId, long compIndex);

    /**
     * Returns given student's StudentExams.
     *
     * @param studentId long representing the student's ID
     * @return List<StudentExam> StudentExams of the student with studentId
     */
    List<StudentExam> getByStudent(long studentId);

    /**
     * Returns StudentExams on the given exam.
     *
     * @param examId long representing the exam's ID
     * @return List<StudentExam> StudentExams on the exam with examId
     */
    List<StudentExam> getByExam(long examId);

    /**
     * Returns given variant's StudentExams on the given exam.
     *
     * @param examId    long representing the exam's ID
     * @param variantId long representing the variant's ID
     * @return List<StudentExam> StudentExams on the exam with examId and variant with variantId
     */
    List<StudentExam> getByVariant(long examId, long variantId);

    /**
     * Moves the given student on the given exam to the new computer.
     * If new computer is occupied or newCompIndex is not valid, fails.
     *
     * @param studentId    long representing the student's ID
     * @param examId       long representing the exam's ID
     * @param newCompIndex long representing new computer's index
     * @return 0 when successfully changed, -1 when fails.
     */
    int changeComputer(long studentId, long examId, long newCompIndex);
}
