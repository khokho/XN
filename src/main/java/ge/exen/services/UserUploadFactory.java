package ge.exen.services;

import ge.exen.DAO.UserUploadDAO;
import ge.exen.models.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserUploadFactory {

    @Autowired
    IFileWorker fileWorker;

    @Autowired
    UserUploadDAO dao;
    /**
     * @param file   uploaded file
     * @param upload upload class instance
     * @return -1 if something went wrong, 0 if everything was okay
     */
    public int Process(MultipartFile file, Upload upload) {

        String name = RandomNameGenerator.generate(10);
        String dir = "src/main/webapp/resources/files/uploads/exam_" + upload.getExamId() + "/var_" + upload.getVarId();
        String path = fileWorker.storeMultiPartFile(dir, file, name);
        if (path == null) return -1;
        upload.setFileLink(path.substring(16));
        dao.storeupload(upload);

        return 0;
    }
}
