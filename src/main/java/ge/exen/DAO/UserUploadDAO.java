package ge.exen.DAO;

import ge.exen.models.StudentExam;
import ge.exen.models.Upload;

import java.util.List;

public interface UserUploadDAO {

    /**
     * stores uploaded file in database;
     */
    void storeupload(Upload upload);

    /**
     * Gets list of all files uploaded by given user in current exam.
     * @param student
     * @return
     */
    List<Upload> getForUser(StudentExam student);
}
