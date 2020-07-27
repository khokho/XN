package ge.exen.DAO;

import ge.exen.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;


public interface ExamDao {
    public static final long ERR_CONNECTION_FAILURE = -1;
    public static final long ERR_UNKNOWN = -2;
    public static final long OK = 0;

    public static final String[] errorMessages = {
            "OK!",
            "Error: connection to database failed.",
            "Unknown error."
    };
    long create(Exam ex);

    Exam get(long ID);

    /**
     * returns all the info in exam table
     * @return list of exams.
     */
    List<Exam> getAll();
}
