package ge.exen.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This class is what we expect from post as an input during submition
 */
public class PostWriteDTO {

    @NotNull
    private Long examId;
    @NotNull
    private String text;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "writePostDTO{" +
                "exam_id=" + examId +
                ", text='" + text + '\'' +
                '}';
    }
}
