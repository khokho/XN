package ge.exen.models;


public class Chat {
    private long chatId;
    private long studentId;
    private long lectorId;
    private long examId;


    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }


    public long getstudentId() {
        return studentId;
    }

    public void setstudentId(long studentId) {
        this.studentId = studentId;
    }


    public long getlectorId() {
        return lectorId;
    }

    public void setlectorId(long lectorId) {
        this.lectorId = lectorId;
    }

    public long getExamId(){
        return examId;
    }

    public void setExamId(long examId){
        this.examId = examId;
    }
}
