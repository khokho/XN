package ge.exen.services;

import ge.exen.models.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserUploadFactory {

    @Autowired
    FileWorkerInterface fileWorker;

    /**
     * @param file   uploaded file
     * @param upload upload class instance
     * @return -1 if something went wrong, 0 if everything was okay
     */
    public int Process(MultipartFile file, Upload upload) {
        String path = fileWorker.storeMultiPartFile("./files/uploads/exam_" + upload.getExamId() + " var_" + upload.getVarId() + "/exam_materials", file, null);
        if (path == null) return -1;
        else return 0;
    }
}
