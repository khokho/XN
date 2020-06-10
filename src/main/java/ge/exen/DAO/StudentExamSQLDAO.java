package ge.exen.DAO;

import ge.exen.models.StudentExam;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentExamSQLDAO extends AbstractSQLDAO implements StudentExamDAO{
    @Override
    public void create(StudentExam studentExam) {

    }

    @Override
    public StudentExam get(long studentId, long examId) {
        return null;
    }

    @Override
    public StudentExam getByComputer(long examId, long compIndex) {
        return null;
    }

    @Override
    public List<StudentExam> getByStudent(long studentId) {
        return null;
    }

    @Override
    public List<StudentExam> getByExam(long examId) {
        return null;
    }

    @Override
    public List<StudentExam> getByVariant(long examId, long variantId) {
        return null;
    }

    @Override
    public int changeComputer(long studentId, long examId, long newCompIndex) {
        return 0;
    }
}
