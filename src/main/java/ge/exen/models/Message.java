package ge.exen.models;


public class Message {

  private long messageId;
  private long from;
  private long chatId;
  private java.sql.Timestamp sentDate;
  private String text;
  private String type;

  @Override
  public String toString() {
    return "Message{" +
            "messageId=" + messageId +
            ", from=" + from +
            ", chatId=" + chatId +
            ", sentDate=" + sentDate +
            ", text='" + text + '\'' +
            ", type='" + type + '\'' +
            '}';
  }

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long messageId) {
    this.messageId = messageId;
  }


  public long getFrom() {
    return from;
  }

  public void setFrom(long from) {
    this.from = from;
  }


  public long getChatId() {
    return chatId;
  }

  public void setChatId(long chatId) {
    this.chatId = chatId;
  }


  public java.sql.Timestamp getSentDate() {
    return sentDate;
  }

  public void setSentDate(java.sql.Timestamp sentDate) {
    this.sentDate = sentDate;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
