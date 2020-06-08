package ge.exen.Model;

import ge.exen.Objects.Exam;
import ge.exen.dao.ExamDao;
import ge.exen.dao.ExamMaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;


import java.sql.SQLException;
import java.util.List;

@Component
public class ExamFactory {

    @Autowired
    ExamDao exdao;
    @Autowired
    ExamMaterialDao materials;
    public static final int STATUS_ERR = -1;
    public static final int STATUS_DB_ERR = -2;
    public static final int STATUS_FILE_DB_ERR = -3;


    /**
     * Processes given data and calls DAO to add exam data to DB.
     *
     * @return newly created positive object ID or negative error code.
     */
    public long process(String name, String startDate, String durHours, String durMinutes, String variants, List<MultipartFile> files) {
        startDate = startDate.replace('T', ' ');
        System.out.println(startDate);

        Integer durationInMinutes = Integer.parseInt(durMinutes) + 60 * Integer.parseInt(durHours);
        Integer variantCount = Integer.parseInt(variants);


        Exam newex = new Exam(name, startDate, durationInMinutes, variantCount, null);
        newex.setID(exdao.create(newex));
        long newID = newex.getID();
        if (newID < 0) return STATUS_DB_ERR;

        newex.setStatementLinks(FileWorker.storeFiles(files, "./files/exam" + newID + "/exam_materials"));

        try {
            materials.create(newex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return STATUS_FILE_DB_ERR;
        }


        return newID;
    }
}
