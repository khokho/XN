package ge.exen.models;

public class ExamLecturers {

  private long examId;
  private long lecturerId;


  public ExamLecturers(long examId, long lecturerId){
      this.examId = examId;
      this.lecturerId = lecturerId;
  }

  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public long getLecturerId() {
    return lecturerId;
  }

  public void setLecturerId(long lecturerId) {
    this.lecturerId = lecturerId;
  }

  @Override
  public String toString() {
    return "ExamLecturers{" +
            "examId=" + examId +
            ", lecturerId=" + lecturerId +
            '}';
  }
}
