package ge.exen.services;

import ge.exen.DAO.ExamMaterialDao;
import ge.exen.DAO.SQLExamMaterialDao;
import ge.exen.models.ExamMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamMaterialService implements IExamMaterial {
    @Autowired
    IFileWorker fileWorker;
    @Autowired
    ExamMaterialDao materials;


    @Override
    public void setMaterial(long var, long id, String name) {
        ExamMaterial mat = new ExamMaterial();
        mat.setExamId(id);
        mat.setVar(var);
        mat.setMaterialLink(name);
        if(getMaterial(var, id) != null) materials.update(mat);
        else materials.create(mat);
    }

    @Override
    public String getMaterial(long var, long examID) {
        ExamMaterial mat = materials.get(examID, var);
        if(mat == null) return null;
        return mat.getMaterialLink();
    }


}
