package ge.exen.services;

import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IExamService {

    /**
     * Processes given data and calls DAO to add exam data to DB.
     *
     * @return newly created positive object ID or negative error code.
     */
    long process(ExamDTO values);

    /**
     * Gets input data and sends it to Exam materials service
     * @param input hashmap of files
     * @param id exam ID
     * @return output message.
     */
    int setFiles(Map<String, MultipartFile> input, Long id);

    /**
     * Gets current user and returns exam
     * @return current user's exam
     */
    StudentExam getCurrentExam();

    /**
     * Gets all live student exams
     * @return all student live exams
     */
    List<Exam> getAllLiveExams();
}
