package ge.exen.DAO;

import ge.exen.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component("exdao")
public class SQLExamDao extends AbstractSQLDAO implements ExamDao {


    @Override
    public long create(Exam ex) {

        String query = "INSERT INTO exam (start_time, duration, var_num, exam_subj) VALUES ( STR_TO_DATE(\"" + ex.getStartDate() + "\", \"%Y/%m/%d %H:%i\"), " + ex.getDurationInMinutes() + ", " +
                ex.getVariants() + ", '" + ex.getFullName() + "')";



        PreparedStatement insertStatement;
        try {
            insertStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
        String query = "SELECT exam_id, DATE_FORMAT(start_time, \"%Y/%m/%d %H:%i\") start_time, exam_subj, var_num, duration FROM exam WHERE exam_id =" +  ID + ";";

        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(query);
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
}
