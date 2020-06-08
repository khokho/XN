package ge.exen.models;

import ge.exen.DAO.SQLExamDao;
import ge.exen.DAO.SQLExamMaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Component
public class ExamFactory {

    @Autowired
    SQLExamDao exdao;
    @Autowired
    SQLExamMaterialDao materials;
    public static final int STATUS_ERR = -1;
    public static final int STATUS_DB_ERR = -2;
    public static final int STATUS_FILE_DB_ERR = -3;


    /**
     * Processes given data and calls DAO to add exam data to DB.
     *
     * @return newly created positive object ID or negative error code.
     */
    public String process(String name, String startDate, String durHours, String durMinutes, String variants, List<MultipartFile> files) {
        startDate = startDate.replace('T', ' ');
        System.out.println(startDate);

        int durationInMinutes = Integer.parseInt(durMinutes) + 60 * Integer.parseInt(durHours);
        int variantCount = Integer.parseInt(variants);


        Exam newex = new Exam(name, startDate, durationInMinutes, variantCount);
        String message = "";
        long val = exdao.create(newex);
        if (val < 0) {
            if (val == SQLExamDao.ERR_CONNECTION_FAILURE)
                message = "Error: Connection to database failed";
            if (val == SQLExamDao.ERR_UNKNOWN) message = "Error";
            return message;
        }

        long newID = newex.getID();

        List<String> paths = ge.exen.models.FileWorker.storeFiles(files, "./files/exam" + newID + "/exam_materials");

        if (paths == null) {
            return "Error: File storage process failed.";
        }
        int index = 1;
        int badFiles = 0;
        for (String path : paths) {
            if (materials.create(new ExamMaterial(path, index, newID)) == SQLExamMaterialDao.ERROR)
                badFiles++;

            index++;
        }

        if (badFiles == 0) return "Exam created successfully";
        else return "Exam created successfully;\n" + "storage of " + badFiles + " files failed.";
    }
}
