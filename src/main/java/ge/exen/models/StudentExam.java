package ge.exen.models;


import java.util.Objects;

public class StudentExam {

  private long studentId;
  private long examId;
  private long variant;
  private long compIndex;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StudentExam)) return false;
    StudentExam that = (StudentExam) o;
    return studentId == that.studentId &&
            examId == that.examId &&
            variant == that.variant &&
            compIndex == that.compIndex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, examId, variant, compIndex);
  }

  /*
  public StudentExam(long studentId, long examId, long variant, long compIndex) {
    this.studentId = studentId;
    this.examId = examId;
    this.variant = variant;
    this.compIndex = compIndex;
  }
*/

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
