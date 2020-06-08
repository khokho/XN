package ge.exen.models;


public class Exam {

  private long examId;
  private java.sql.Date startTime;
  private java.sql.Date endTime;
  private long varNum;
  private String examSubj;


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public java.sql.Date getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Date startTime) {
    this.startTime = startTime;
  }


  public java.sql.Date getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Date endTime) {
    this.endTime = endTime;
  }


  public long getVarNum() {
    return varNum;
  }

  public void setVarNum(long varNum) {
    this.varNum = varNum;
  }


  public String getExamSubj() {
    return examSubj;
  }

  public void setExamSubj(String examSubj) {
    this.examSubj = examSubj;
  }

}
