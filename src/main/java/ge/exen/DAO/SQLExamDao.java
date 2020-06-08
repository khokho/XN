package ge.exen.DAO;

import ge.exen.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component("exdao")
public class SQLExamDao implements ExamDao {
    @Autowired
    private DataSource db;

    @Override
    public long create(Exam ex) {

        String query = "INSERT INTO exam (start_time, duration, var_num, exam_subj) VALUES ('" +
                ex.getStartDate() + "', " + ex.getDurationInMinutes() + ", " +
                ex.getVariants() + ", '" + ex.getFullName() + "')";


        Connection conn;
        try {
            conn = db.getConnection();
        } catch (SQLException throwables) {
            return ERR_CONNECTION_FAILURE;
        }
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
            System.out.println(ans.getRow());
            if (ans.next()) {
                ex.setID(ans.getLong(1));
            } else return ERR_UNKNOWN;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERR_UNKNOWN;
        }

        return OK;
    }
}
