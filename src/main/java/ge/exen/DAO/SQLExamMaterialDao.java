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

    @Override
    public ExamMaterial get(long id, int var) {
        String query = "SELECT *  FROM exam_materials WHERE exam_id =" +  id + " AND var = " + var +";";

        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(query);
            if(!res.next()) {return null;}
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

}
