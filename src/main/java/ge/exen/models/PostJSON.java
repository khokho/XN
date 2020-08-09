package ge.exen.models;

import java.util.Date;

public class PostJSON {
    public static String REMOVE = "remove";
    public static String ADD = "add";
    public static String EDIT = "edit";
    public static String BUG = "bug";
    String exam;
    String lecturer;
    Date date;
    String text;
    long postId;
    String action;
    long fromId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        if(action.equals(REMOVE))
            this.action = REMOVE;
        else if(action.equals(ADD))
            this.action = ADD;
        else if(action.equals(EDIT))
            this.action = EDIT;
        else this.action = BUG;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }
}
