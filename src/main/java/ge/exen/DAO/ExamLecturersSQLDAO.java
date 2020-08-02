package ge.exen.DAO;

import ge.exen.models.ExamLecturers;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement st = conn.prepareStatement("SELECT * FROM exam_lecturers WHERE exam_id = ? and lecturer_id = ?;");
            st.setLong(1, examLecturers.getExamId());
            st.setLong(2, examLecturers.getLecturerId());
            return st.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getLecturerIds(long examId){
        try {
            List<Long> res = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT lecturer_id FROM exam_lecturers WHERE exam_id = ?;");
            st.setLong(1, examId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                res.add(rs.getLong(1));
            }
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
