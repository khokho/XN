package ge.exen.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface ExamMaterialInterface {

    int storeFiles(HashMap<Integer, MultipartFile> fileMap, Long id);

}
