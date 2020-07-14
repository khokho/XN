package ge.exen.services;

import ge.exen.DAO.SQLExamMaterialDao;
import ge.exen.models.ExamMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamMaterialService implements ExamMaterialInterface {
    @Autowired
    FileWorkerInterface fileWorker;
    @Autowired
    SQLExamMaterialDao materials;

    public static final String directory = "./files/exam%id%/exam_materials";

    public int storeFiles(HashMap<Integer, MultipartFile> fileMap, Long id){
        int faultyFiles = 0;
        String dir = directory.replace("%id%", id.toString());

        for (Map.Entry<Integer, MultipartFile> ent : fileMap.entrySet()){

            String path = fileWorker.storeMultiPartFile(dir, ent.getValue(), null);
            if(path == null){
                faultyFiles ++;
                continue;
            }
            ExamMaterial newMat = new ExamMaterial(path, ent.getKey(), id);
            materials.create(newMat);
        }
        return faultyFiles;
    }
}
