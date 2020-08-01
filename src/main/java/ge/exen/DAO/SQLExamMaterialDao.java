package ge.exen.DAO;

import ge.exen.models.Exam;
import ge.exen.models.ExamMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component("exmatDao")
public class SQLExamMaterialDao extends AbstractSQLDAO implements ExamMaterialDao {

    private final String insertTemplate = "INSERT into exam_materials (material_link, var, exam_id) VALUES (?, ?, ?)";


    @Override
    public int create(ExamMaterial material) {
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement(insertTemplate, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERROR;
        }

        try {
            stat.setString(1, material.getMaterialLink());
            stat.setLong(2, material.getVar());
            stat.setLong(3, material.getExamId());
            stat.executeUpdate();
            ResultSet res = stat.getGeneratedKeys();
            if (res.next()) material.setExamId(res.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ERROR;
        }

        return OK;
    }

    @Override
    public ExamMaterial get(long id, long var) {
        String query = "SELECT *  FROM exam_materials WHERE exam_id = ? AND var = ?;";

        try {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setLong(1, id);
            stat.setLong(2, var);
            ResultSet res = stat.executeQuery();
            if (!res.next()) {
                return null;
            }
            ExamMaterial ret = new ExamMaterial();
            ret.setVar(res.getInt("var"));
            ret.setExamId(res.getInt("exam_id"));
            ret.setMaterialId(res.getInt("material_id"));
            ret.setMaterialLink(res.getString("material_link"));
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(ExamMaterial mat) {
        String upd = "UPDATE exam_materials SET material_link = ? WHERE exam_id = ? AND var = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(upd);
            stat.setString(1, mat.getMaterialLink());
            stat.setLong(2, mat.getExamId());
            stat.setLong(3, mat.getVar());
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(long id, long newVar) {
        String upd = "DELETE FROM exam_materials WHERE exam_id = ? AND var > ?";

        try {
            PreparedStatement stat = conn.prepareStatement(upd);
            stat.setLong(1, id);
            stat.setLong(2, newVar);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}