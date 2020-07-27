package ge.exen.DAO;

import ge.exen.models.ExamLecturers;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ExamLecturersSQLDAO extends AbstractSQLDAO implements ExamLecturersDAO{
    @Override
    public boolean create(ExamLecturers examLecturers) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO exam_lecturers  VALUES(?,?);");
            st.setLong(1, examLecturers.getExamId());
            st.setLong(2, examLecturers.getLecturerId());
            if (st.executeUpdate() == 0) throw new SQLException("something went wrong while inserting a pair of exam and a lecturer");
            return true;
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean check(ExamLecturers examLecturers) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT* FROM exam_lecturers WHERE exam_id = ? and lecturer_id = ?;");
            st.setLong(1, examLecturers.getExamId());
            st.setLong(2, examLecturers.getLecturerId());
            return st.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
