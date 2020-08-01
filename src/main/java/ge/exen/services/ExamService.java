package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.StudentExamDAO;
import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import ge.exen.models.ExamLecturers;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    @Autowired
    ExamLecturersDAO examLecturersDAO;


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
                files.put(Integer.parseInt(ent.getKey().substring(10)), ent.getValue());
            }
        }

        return materials.storeFiles(files, id);
    }


    public StudentExam getExamForCurrentUser() {
        User user = userService.getCurrentUser();
        List<Exam> exams = dao.getAll();
        for (int i = 0; i < exams.size(); i++) {
           //if(!isCurrentlyLive(exams.get(i))) continue;
           System.out.println(user.getId()+ " " + exams.get(i).getID());
            StudentExam exam = studentExamDao.get(user.getId(), exams.get(i).getID());
            if (exam != null) return exam;
        }

        return null;
    }

    public StudentExam getLiveExamForCurrentStudent() {
        User user = userService.getCurrentUser();
        List<Exam> exams = dao.getAll();
        for (int i = 0; i < exams.size(); i++) {
            if(!isCurrentlyLive(exams.get(i))) continue;
            System.out.println(user.getId()+ " " + exams.get(i).getID());
            StudentExam exam = studentExamDao.get(user.getId(), exams.get(i).getID());
            if (exam != null) return exam;
        }

        return null;
    }

    public List<ExamLecturers> getLiveExamForCurrentLecturer() {
        List<ExamLecturers> curExams = new ArrayList<>();
        User user = userService.getCurrentUser();
        List<Exam> exams = dao.getAll();
        for (int i = 0; i < exams.size(); i++) {
            if(!isCurrentlyLive(exams.get(i))) continue;
            System.out.println(user.getId()+ " " + exams.get(i).getID());
            ExamLecturers exam = new ExamLecturers(exams.get(i).getID(), user.getId());
            if(examLecturersDAO.check(exam))
                curExams.add(exam);
        }

        return curExams;
    }

    public List<Exam> getExamsForHighStatus() {
        User user = userService.getCurrentUser();
        List<Exam> ans = new ArrayList<>();
        List<Exam> exams = dao.getAll();
        String status = user.getStatus();
        if(status.equals("student")) return ans;
        if(status.equals("admin")) return exams;
        for(int i = 0; i < exams.size(); i++) {
             if(examLecturersDAO.check(new ExamLecturers(exams.get(i).getID(), user.getId()))) ans.add(exams.get(i));
        }
        return ans;
    }

    public List<Exam> getLiveExamsForHighStatus() {
        User user = userService.getCurrentUser();
        List<Exam> ans = new ArrayList<>();
        List<Exam> exams = dao.getAll();
        String status = user.getStatus();
        if(status.equals("student")) return ans;
        for(int i = 0; i < exams.size(); i++) {
            if(!isCurrentlyLive(exams.get(i))) continue;
            if(status.equals("admin")) {
                ans.add(exams.get(i));
                continue;
            }
            if(examLecturersDAO.check(new ExamLecturers(exams.get(i).getID(), user.getId()))) ans.add(exams.get(i));
        }
        return ans;
    }


    public List<Exam> getAllCurrentExams() {
        List<Exam> exams = dao.getAll();
        List<Exam> ans = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
            if (isCurrentlyLive(exams.get(i))) ans.add(exams.get(i));
        }
        return ans;
    }

    public List<Exam> getAllPastExams() {
        List<Exam> exams = dao.getAll();
        List<Exam> ans = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
            if (!isCurrentlyLive(exams.get(i))) ans.add(exams.get(i));
        }
        return ans;
    }

    @Override
    public List<Exam> getAllExams() {
        return dao.getAll();
    }

    public boolean isCurrentlyLive(Exam exam) {
        int duration = exam.getDurationInMinutes();
        LocalDateTime now = LocalDateTime.now();
        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(exam.getStartDate());
            LocalDateTime date1 = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            Long diff = ChronoUnit.MINUTES.between(date1, now);
            return diff >= 0 && diff <= duration;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
