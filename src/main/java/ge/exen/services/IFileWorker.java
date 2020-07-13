package ge.exen.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface IFileWorker {
    /**
     * Stores singular MultiPartFile in given directory.
     *
     * @param file MultiPartFile to be stored
     * @param directory destination folder (with or without '/' at the end)
     * @param name name of the file (without extension)
     * @return path of stored file
     */

    String storeMultiPartFile(String directory, MultipartFile file, String name);


}
