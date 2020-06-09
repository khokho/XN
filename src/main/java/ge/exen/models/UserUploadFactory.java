package ge.exen.models;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserUploadFactory {

    /**
     * @param file   uploaded file
     * @param upload upload class instance
     * @return -1 if something went wrong, 0 if everything was okay
     */
    public static int Process(MultipartFile file, Upload upload) {
        String path = FileWorker.storeMultiPartFile("./files/uploads/exam_" + upload.getExamId() + " var_" + upload.getVarId() + "/exam_materials", file);
        if (path == null) return -1;
        else return 0;
    }
}
