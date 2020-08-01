package ge.exen.models;


import java.sql.Timestamp;

public class Upload {

  private long uploadId;
  private long fromId;
  private long examId;
  private long varId;
  private Timestamp time;
  private String fileLink;


  public long getUploadId() {
    return uploadId;
  }

  public void setUploadId(long uploadId) {
    this.uploadId = uploadId;
  }


  public void setTime(Timestamp tm) {this.time = tm;}

  public Timestamp getTime() { return this.time; }

  public long getFromId() {
    return fromId;
  }

  public void setFromId(long fromId) {
    this.fromId = fromId;
  }


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public long getVarId() {
    return varId;
  }

  public void setVarId(long varId) {
    this.varId = varId;
  }


  public String getFileLink() {
    return fileLink;
  }

  public void setFileLink(String fileLink) {
    this.fileLink = fileLink;
  }

}
