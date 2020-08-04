package ge.exen.DAO;

import ge.exen.models.Exam;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component("exdao")
public class SQLExamDao extends AbstractSQLDAO implements ExamDao {


    @Override
    public long create(Exam ex) {
        String query = "INSERT INTO exam (start_time, duration, var_num, exam_subj) VALUES ( STR_TO_DATE( ? , '%Y/%m/%d %H:%i'), ?, ?, ?)";



        PreparedStatement insertStatement;
        try {
            insertStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, ex.getStartDate());
            insertStatement.setInt(2, ex.getDurationInMinutes());
            insertStatement.setInt(3, ex.getVariants());
            insertStatement.setString(4, ex.getFullName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERR_UNKNOWN;
        }
        ResultSet ans;

        try {
            insertStatement.executeUpdate();
            ans = insertStatement.getGeneratedKeys();
            ans.next();
            ex.setID(ans.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERR_UNKNOWN;
        }

        try {
            insertStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return OK;
    }

    @Override
    public Exam get(long ID) {
        String query = "SELECT exam_id, DATE_FORMAT(start_time, '%Y/%m/%d %H:%i') start_time, exam_subj, var_num, duration FROM exam WHERE exam_id = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, ID);
            ResultSet res = stat.executeQuery();
            if(!res.next()) {return null;}
            Exam ret = new Exam();
            ret.setID(ID);
            ret.setDuration(res.getInt("duration"));
            ret.setName(res.getString("exam_subj"));
            ret.setStartDate(res.getString("start_time"));
            ret.setVariants(res.getInt("var_num"));
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Exam> getAll() {
        String query = "SELECT exam_id, DATE_FORMAT(start_time, '%Y/%m/%d %H:%i') start_time, exam_subj, var_num, duration FROM exam order by start_time";
        List<Exam> ans = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                Exam exam = new Exam();
                exam.setID(res.getInt("exam_id"));
                exam.setDuration(res.getInt("duration"));
                exam.setName(res.getString("exam_subj"));
                exam.setStartDate(res.getString("start_time"));
                exam.setVariants(res.getInt("var_num"));
                ans.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
