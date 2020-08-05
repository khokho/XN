package ge.exen.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface IExamMaterial {

    void setMaterial(long var, long id, String name);

    String getMaterial(long var, long examID);
}
