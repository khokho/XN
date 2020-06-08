package ge.exen.DAO;

import ge.exen.models.ExamMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("exmatDao")
public class SQLExamMaterialDao implements ExamMaterialDao {
    @Autowired
    private DataSource db;
    private final String insertTemplate = "INSERT into exam_materials (material_link, var, exam_id) VALUES (?, ?, ?)";


    @Override
    public int create(ExamMaterial material) {
        Connection conn;
        PreparedStatement stat;
        try {
            conn = db.getConnection();
            stat = conn.prepareStatement(insertTemplate, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERROR;
        }

        try {
            stat.setString(1, material.getMaterialLink());
            stat.setInt(2, (int) material.getVar());
            stat.setInt(3, (int) material.getExamId());
            stat.executeUpdate();
            ResultSet res = stat.getGeneratedKeys();
            if (res.next()) material.setExamId(res.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERROR;
        }
        return OK;
    }

}
