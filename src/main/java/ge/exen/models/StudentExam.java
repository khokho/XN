package ge.exen.models;


public class StudentExam {

  private long studentId;
  private long examId;
  private long variant;
  private long compIndex;


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public long getVariant() {
    return variant;
  }

  public void setVariant(long variant) {
    this.variant = variant;
  }


  public long getCompIndex() {
    return compIndex;
  }

  public void setCompIndex(long compIndex) {
    this.compIndex = compIndex;
  }

}
