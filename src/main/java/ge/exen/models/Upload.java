package ge.exen.models;


public class Upload {

  private long uploadId;
  private long fromId;
  private long examId;
  private long varId;
  private long fileLink;


  public long getUploadId() {
    return uploadId;
  }

  public void setUploadId(long uploadId) {
    this.uploadId = uploadId;
  }


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


  public long getFileLink() {
    return fileLink;
  }

  public void setFileLink(long fileLink) {
    this.fileLink = fileLink;
  }

}
