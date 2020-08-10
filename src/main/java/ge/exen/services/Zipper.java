package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.DAO.UserDAO;
import ge.exen.DAO.UserUploadDAO;
import ge.exen.models.StudentExam;
import ge.exen.models.Upload;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class Zipper {
    @Autowired
    private UserUploadDAO uploads;

    @Autowired
    private StudentExamDAO examUsers;

    @Autowired
    private UserDAO users;

    @Autowired
    private UserService current;

    @Autowired
    private ExamDao exams;

    int numerator = 0;


    private String getSuffix(String name){
        while(name.contains(".")){
            name = name.substring(name.indexOf(".") + 1);
        }

        return "." + name;
    }

    public String doZip(int examID) throws IOException {
        String folder = RandomNameGenerator.generate(10);
        File dir = new File("src/main/webapp/resources/files/downloads/" + folder + "/");
        if (!current.getCurrentUser().getStatus().equals(User.LECTURER)) return "";
        try {
            if(!dir.mkdirs())
                System.out.println("folder was not created");
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        String zip = "src/main/webapp/resources/files/downloads/" + folder + "/all_student_works.zip";
        File zz = new File(zip);
        if (zz.exists()) {
            try {
                zz.delete();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }


        List<StudentExam> ls = examUsers.getByExam(examID);
        FileOutputStream fos = new FileOutputStream(new File(zip));
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        HashSet<Long> variants = new HashSet<>();
        for (StudentExam entry : ls) {
            List<Upload> curr = uploads.getForUser(entry);
            String path = "src/main/webapp/";
            String suffix;
            if (curr.size() == 0) continue;
            else {
                path += curr.get(curr.size() - 1).getFileLink();
                suffix = getSuffix(curr.get(curr.size() - 1).getFileLink());
            }
            if (!variants.contains(entry.getVariant())) {
                variants.add(entry.getVariant());
                zipOut.putNextEntry(new ZipEntry(exams.get(examID).getFullName() + "/var_" + entry.getVariant() + "/"));
                zipOut.closeEntry();
            }
            String name = users.getUser(entry.getStudentId()).getEmail();
            zipFile(new File(path), exams.get(examID).getFullName() + "/var_" + entry.getVariant() + "/" + name.substring(0, name.indexOf("@")) + suffix, zipOut);
        }
        zipOut.close();
        fos.close();
        return zip;
    }


    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileToZip);

        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
}

}
