package ge.exen.DAO;

import ge.exen.models.StudentExam;
import ge.exen.models.Upload;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLUserUploadDAO extends AbstractSQLDAO implements UserUploadDAO {
    private final String insertTemplate = "INSERT into upload (from_id, exam_id, var_id, file_link, time) VALUES (?, ?, ?, ?, ?)";
    private final String queryTemplate = "SELECT * from upload where exam_id = ? AND from_id = ? order by time";

    @Override
    public void storeupload(Upload upload) {
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement(insertTemplate, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }

        try {
            stat.setLong(1, upload.getFromId());
            stat.setLong(2, upload.getExamId());
            stat.setLong(3, upload.getVarId());
            stat.setString(4, upload.getFileLink());
            stat.setTimestamp(5, upload.getTime());
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Upload> getForUser(StudentExam student) {
        PreparedStatement stat;
        try{
            stat = conn.prepareStatement(queryTemplate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        try {
             stat.setLong(1, student.getExamId());
            stat.setLong(2, student.getStudentId());

            ResultSet res = stat.executeQuery();

            List<Upload> ret = new ArrayList<>();
            while (res.next()){
                Upload up = new Upload();
                up.setFromId(res.getLong(2));
                up.setExamId(res.getLong(3));
                up.setVarId(res.getLong(4));
                up.setFileLink(res.getString(5));
                up.setTime(res.getTimestamp(6));

                ret.add(up);
            }
            return  ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
