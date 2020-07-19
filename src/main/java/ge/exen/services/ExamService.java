package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExamService implements IExamService {

    @Autowired
    ExamDao dao;
    @Autowired
    IExamMaterial materials;
    @Autowired
    IUserService userService;
    @Autowired
    StudentExamDAO studentExamDao;


    public long process(ExamDTO values) {
        String polishedDate = values.getStartDate().replace('T', ' ');
        values.setStartDate(polishedDate);

        int durationInMinutes = values.getMinutes() + 60 * values.getHours();

        Exam newex = new Exam(values.getFullName(),
                values.getStartDate(),
                durationInMinutes,
                values.getVariants());


        long val = dao.create(newex);
        if (val < 0) return val;


        return newex.getID();
    }


    public int setFiles(Map<String, MultipartFile> input, Long id) {
        HashMap<Integer, MultipartFile> files = new HashMap<>();
        for (Map.Entry<String, MultipartFile> ent : input.entrySet()) {

            if (!ent.getValue().isEmpty()) {
                System.out.println(ent.getKey());
                files.put(Integer.parseInt(ent.getKey()), ent.getValue());
            }
        }

        return materials.storeFiles(files, id);
    }


    public StudentExam getCurrentExam() {
        User user = userService.getCurrentUser();
        List<Exam> exams = dao.getAll();
        for (int i = 0; i < exams.size(); i++) {
            StudentExam exam = studentExamDao.get(user.getId(), exams.get(i).getID());
            if (exam != null) return exam;
        }
        return null;
    }


    public List<Exam> getAllLiveExams() {
        List<Exam> exams = dao.getAll();
        List<Exam> ans = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
            String startDate = exams.get(i).getStartDate();
            int duration = exams.get(i).getDurationInMinutes();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String currentTime = dtf.format(now);
            int indexOfSpace1 = startDate.indexOf(" ");
            int indexOfSpace2 = currentTime.indexOf(" ");
            String date1 = startDate.substring(0, indexOfSpace1);
            String date2 = currentTime.substring(0, indexOfSpace2);
          //  System.out.println("axla droa: " + currentTime + "  axla: " + startDate + " shemcirebulebi " + date2 + " " + date1);
            if (date1.equals(date2)) {
                String inHours = startDate.substring(indexOfSpace1 + 1);
                String inHours1 = currentTime.substring(indexOfSpace2 + 1);
               // System.out.println(inHours + " " + inHours1);
                int inMinutes = 60 * Integer.parseInt(inHours.substring(0, inHours.indexOf(":"))) +
                        Integer.parseInt(inHours.substring(inHours.indexOf(":") + 1));
                int inMinutesNow = 60 * Integer.parseInt(inHours1.substring(0, inHours1.indexOf(":"))) +
                        Integer.parseInt(inHours1.substring(inHours1.indexOf(":") + 1));
               // System.out.println("match moxda : " + date1 + " " + date2 + " " + inMinutes + " " + inMinutesNow);
                if (inMinutes + duration <= inMinutesNow) ans.add(exams.get(i));
            }
        }
        return ans;
    }
}
