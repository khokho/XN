package ge.exen.dao;

import ge.exen.Objects.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component("exmatDao")
public class ExamMaterialDao {
    @Autowired
    private DataSource db;
    private String insertTemplate = "INSERT into exam_materials (material_link, var, exam_id) VALUES (?, ?, ?)";

    public void create(Exam ex) throws SQLException {
        List<String> paths = ex.getStatementLinks();
        Connection conn = db.getConnection();
        PreparedStatement stat = conn.prepareStatement(insertTemplate);
        int variantNum = 0;
        System.out.println("!!!" + paths.size());
        for (String path : paths) {
            //skip first null element as enumeration starts from 1.
            if (variantNum == 0) {
                variantNum++;
                continue;
            }
            stat.setString(1, path);
            stat.setInt(2, variantNum);
            stat.setInt(3, (int) ex.getID());

            stat.executeUpdate();

            variantNum++;

        }
    }
}
