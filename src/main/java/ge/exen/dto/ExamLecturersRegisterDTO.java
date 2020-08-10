package ge.exen.dto;

import javax.validation.constraints.NotNull;

public class ExamLecturersRegisterDTO {
    @NotNull
    private Long examId;
    @NotNull
    private String lecturerMail;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getLecturerMail() {
        return lecturerMail;
    }

    public void setLecturerMail(String lecturerMail) {
        this.lecturerMail = lecturerMail;
    }

    @Override
    public String toString() {
        return examId + " " + lecturerMail;
    }
}
