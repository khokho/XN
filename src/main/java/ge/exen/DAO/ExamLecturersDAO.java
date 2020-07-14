package ge.exen.DAO;

import ge.exen.models.ExamLecturers;

public interface ExamLecturersDAO {
    /**
     * @param examLecturers pair of exam_id and lecturer_id
     * @return
     * adds given pair to the database
     */
    boolean create(ExamLecturers examLecturers);

    /**
     * @param examLecturers pair of exam_id and lecturer_id
     * @return  true if the given pair exists in the database
     *          false if it doesn't exist
     */
    boolean check(ExamLecturers examLecturers);
}
