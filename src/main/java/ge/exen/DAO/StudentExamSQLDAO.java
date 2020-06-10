package ge.exen.DAO;

import ge.exen.models.Chat;
import ge.exen.models.StudentExam;
import org.springframework.stereotype.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentExamSQLDAO extends AbstractSQLDAO implements StudentExamDAO {
    @Override
    public void create(StudentExam studentExam) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("INSERT INTO student_exam VALUES(?, ?, ?, ?)");
            prStmt.setLong(1, studentExam.getStudentId());
            prStmt.setLong(2, studentExam.getExamId());
            prStmt.setLong(3, studentExam.getVariant());
            prStmt.setLong(4, studentExam.getCompIndex());

            if (prStmt.executeUpdate() != 0) {
                throw new SQLException("StudentExam could not be added in the DB.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StudentExam get(long studentId, long examId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM student_exam WHERE student_id = ? AND exam_id = ?");
            prStmt.setLong(1, studentId);
            prStmt.setLong(2, examId);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("StudentExam with given studentId and examId could not be retrieved.");
            }
            return resultSetToStudentExam(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentExam getByComputer(long examId, long compIndex) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM student_exam WHERE exam_id = ? AND comp_index = ?");
            prStmt.setLong(1, examId);
            prStmt.setLong(2, compIndex);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("StudentExam with given examId and compIndex could not be retrieved.");
            }
            return resultSetToStudentExam(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StudentExam> getByStudent(long studentId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM student_exam WHERE student_id = ?");
            prStmt.setLong(1, studentId);
            ResultSet rs = prStmt.executeQuery();

            List<StudentExam> studentExams = new ArrayList<>();
            while (rs.next()) {
                studentExams.add(resultSetToStudentExam(rs));
            }
            return studentExams;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StudentExam> getByExam(long examId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM student_exam WHERE exam_id = ?");
            prStmt.setLong(1, examId);
            ResultSet rs = prStmt.executeQuery();

            List<StudentExam> studentExams = new ArrayList<>();
            while (rs.next()) {
                studentExams.add(resultSetToStudentExam(rs));
            }
            return studentExams;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StudentExam> getByVariant(long examId, long variantId) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("SELECT * FROM student_exam WHERE exam_id = ? AND variant = ?");
            prStmt.setLong(1, examId);
            prStmt.setLong(2, variantId);
            ResultSet rs = prStmt.executeQuery();

            List<StudentExam> studentExams = new ArrayList<>();
            while (rs.next()) {
                studentExams.add(resultSetToStudentExam(rs));
            }
            return studentExams;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int changeComputer(long studentId, long examId, long newCompIndex) {
        PreparedStatement prStmt;
        try {
            prStmt = db.getConnection().prepareStatement("UPDATE student_exam SET comp_index = ? WHERE student_id = ? AND exam_id = ?");
            prStmt.setLong(1, newCompIndex);
            prStmt.setLong(2, studentId);
            prStmt.setLong(3, examId);

            if (prStmt.executeUpdate() != 0) {
                throw new SQLException("Studen's computer on this exam could not be changed.");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Given a ResultSet, returns corresponding StudentExam.
     *
     * @param rs ResultSet coming from student_exam table
     * @return StudentExam representing the current row in the ResultSet
     * @throws SQLException if the ResultSet is not valid.
     */
    private StudentExam resultSetToStudentExam(ResultSet rs) throws SQLException {
        StudentExam studentExam = new StudentExam();
        studentExam.setStudentId(rs.getLong(1));
        studentExam.setExamId(rs.getLong(2));
        studentExam.setVariant(rs.getLong(3));
        studentExam.setCompIndex(rs.getLong(4));
        return studentExam;
    }
}
