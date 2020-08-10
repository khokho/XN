package ge.exen.models;

public class QueueJSON {

    long userId;
    String type;

    public QueueJSON(long userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
