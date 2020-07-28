package ge.exen.dto;

public class StudentExamRegisterDTO {
    String studentMail;
    Long examId;
    Long variant;
    Long compIndex;


    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getVariant() {
        return variant;
    }

    public void setVariant(Long variant) {
        this.variant = variant;
    }

    public Long getCompIndex() {
        return compIndex;
    }

    public void setCompIndex(Long compIndex) {
        this.compIndex = compIndex;
    }

    @Override
    public String toString() {
        return "StudentExamRegisterDTO{" +
                "studentMail='" + studentMail + '\'' +
                ", examId=" + examId +
                ", variant=" + variant +
                ", compIndex=" + compIndex +
                '}';
    }
}
