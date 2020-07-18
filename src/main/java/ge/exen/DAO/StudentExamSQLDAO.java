package ge.exen.DAO;

import ge.exen.models.Chat;
import ge.exen.models.StudentExam;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentExamSQLDAO extends AbstractSQLDAO implements StudentExamDAO {
    @Override
    public boolean create(StudentExam studentExam) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("INSERT INTO student_exam VALUES(?, ?, ?, ?)");
            prStmt.setLong(1, studentExam.getStudentId());
            prStmt.setLong(2, studentExam.getExamId());
            prStmt.setLong(3, studentExam.getVariant());
            prStmt.setLong(4, studentExam.getCompIndex());

            if (prStmt.executeUpdate() != 0) {
                throw new SQLException("StudentExam could not be added in the DB.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public StudentExam get(long studentId, long examId) {
        String condition = "student_id = ? AND exam_id = ?";
        long[] columnValues = new long[]{studentId, examId};
        return getStudentExamFromDb(condition, columnValues);
    }

    @Override
    public StudentExam getByComputer(long examId, long compIndex) {
        String condition = "exam_id = ? AND comp_index = ?";
        long[] columnValues = new long[]{examId, compIndex};
        return getStudentExamFromDb(condition, columnValues);
    }

    @Override
    public List<StudentExam> getByStudent(long studentId) {
        String condition = "student_id = ?";
        long[] columnValues = new long[]{studentId};
        return getStudentExamListFromDb(condition, columnValues);
    }

    @Override
    public List<StudentExam> getByExam(long examId) {
        String condition = "exam_id = ?";
        long[] columnValues = new long[]{examId};
        return getStudentExamListFromDb(condition, columnValues);
    }

    @Override
    public List<StudentExam> getByVariant(long examId, long variantId) {
        String condition = "exam_id = ? AND variant = ?";
        long[] columnValues = new long[]{examId, variantId};
        return getStudentExamListFromDb(condition, columnValues);
    }

    /**
     * Given a condition and arguments, returns corresponding StudentExam.
     *
     * @param condition    String representing which columns need to be checked
     * @param columnValues long Array representing values of each column
     * @return StudentExam with given column values
     */
    private StudentExam getStudentExamFromDb(String condition, long[] columnValues) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM student_exam WHERE " + condition);
            for (int i = 0; i < columnValues.length; i++)
                prStmt.setLong(i + 1, columnValues[i]);
            ResultSet rs = prStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Corresponding StudentExam could not be retrieved.");
            }
            return resultSetToStudentExam(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Given a condition and arguments, returns corresponding StudentExams.
     *
     * @param condition    String representing which columns need to be checked
     * @param columnValues long Array representing values of each column
     * @return List<StudentExam> StudentExams with given column values
     */
    private List<StudentExam> getStudentExamListFromDb(String condition, long[] columnValues) {
        PreparedStatement prStmt;
        try {
            prStmt = conn.prepareStatement("SELECT * FROM student_exam WHERE " + condition);
            for (int i = 0; i < columnValues.length; i++)
                prStmt.setLong(i + 1, columnValues[i]);
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
            prStmt = conn.prepareStatement("UPDATE student_exam SET comp_index = ? WHERE student_id = ? AND exam_id = ?");
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
