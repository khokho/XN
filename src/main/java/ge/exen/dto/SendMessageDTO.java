package ge.exen.dto;

public class SendMessageDTO {
    long chatId;
    String type;
    String text;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SendMessageDTO{" +
                ", chatId=" + chatId +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
