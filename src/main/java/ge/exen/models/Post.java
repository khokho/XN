package ge.exen.models;


public class Post {

  private long postId;
  private long examId;
  private long fromId;
  private String text;
  private java.sql.Timestamp date;


  public long getPostId() {
    return postId;
  }

  public void setPostId(long postId) {
    this.postId = postId;
  }


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public long getFromId() {
    return fromId;
  }

  public void setFromId(long fromId) {
    this.fromId = fromId;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  public java.sql.Timestamp getDate() {
    return date;
  }

  public void setDate(java.sql.Timestamp date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Post{" +
            "postId=" + postId +
            ", examId=" + examId +
            ", fromId=" + fromId +
            ", text='" + text + '\'' +
            ", date=" + date +
            '}';
  }
}
