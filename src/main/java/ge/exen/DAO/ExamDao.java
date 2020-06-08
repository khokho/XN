package ge.exen.DAO;

import ge.exen.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public interface ExamDao {
    public static final long ERR_CONNECTION_FAILURE = -1;
    public static final long ERR_UNKNOWN = -2;
    public static final long OK = 0;

    long create(Exam ex);
}
