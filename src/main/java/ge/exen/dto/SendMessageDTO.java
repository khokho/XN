package ge.exen.dto;

public class SendMessageDTO {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";

    private long chatId;
    private String type;
    private String text;

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
