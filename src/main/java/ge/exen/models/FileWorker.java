package ge.exen.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileWorker {
    /**
     * Stores singular MultiPartFile in given directory.
     *
     * @param file
     * @param directory
     * @return path of stored file
     */

    public static String storeMultiPartFile(String directory, MultipartFile file) {
        Path dir = Paths.get(directory);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                return null;
            }
        }
        String ret = directory + "/" + file.getOriginalFilename();
        File material = new File(ret);

        try {
            OutputStream os = new FileOutputStream(material);
            os.write(file.getBytes());
        } catch (IOException e) {
            return null;
        }

        return ret;
    }

    /**
     * Stores given list of MultiPartFiles in given directory.
     *
     * @param files
     * @param dir
     * @return list of paths of stored files.
     */
    public static List<String> storeFiles(List<MultipartFile> files, String dir) {
        List<String> ret = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file == null) {
                ret.add(null);
            } else {
                String path = storeMultiPartFile(dir, file);
                if (path == null) return null;
                ret.add(path);
            }


        }
        return ret;
    }

}
