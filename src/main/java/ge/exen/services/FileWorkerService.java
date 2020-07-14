package ge.exen.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileWorkerService implements IFileWorker {


    public String storeMultiPartFile(String directory, MultipartFile file, String name) {
        Path dir = Paths.get(directory);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                return null;
            }
        }
        String ret = directory;
        if(ret.charAt(ret.length() - 1) != '/') ret = ret + "/";
        if(name == null) ret = ret + file.getOriginalFilename();
        else {
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if(ext == null) ext = "";
            if(ext.length() != 0) ext = "." + ext;
            ret = ret + name + ext;
        }
        File material = new File(ret);

        try {
            OutputStream os = new FileOutputStream(material);
            os.write(file.getBytes());
        } catch (IOException e) {
            return null;
        }

        return ret;
    }



}
