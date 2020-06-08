package ge.exen.dao;

import ge.exen.Objects.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component("exdao")
public class ExamDao {
    @Autowired
    private DataSource db;

    public static final int ERROR = -1;


    public long create(Exam ex) {

        try {

            String query = "INSERT INTO exam (start_time, duration, var_num, exam_subj) VALUES ('" +
                    ex.getStartDate() + "', " + ex.getDurationInMinutes() + ", " +
                    ex.getVariants() + ", '" + ex.getFullName() + "')";


            Connection conn = db.getConnection();
            PreparedStatement insertStatement =
                    conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            insertStatement.executeUpdate();
            ResultSet ans = insertStatement.getGeneratedKeys();
            if (ans.next()) {
                return ans.getLong(1);
            } else return ERROR;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERROR;
        }
    }
}
