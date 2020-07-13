package ge.exen.models;

public class Exam {
    private String fullName;
    private String startDate;
    private Integer durationInMinutes;
    private Integer variants;
    private long id;


    public Exam(String name, String startDate, int durationInMinutes, int variants) {
        this.fullName = name;
        this.startDate = startDate;
        this.durationInMinutes = durationInMinutes;
        this.variants = variants;
    }

    public Exam() {

    }


    public String getFullName() {
        return fullName;
    }

    public String getStartDate() {
        return startDate;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Integer getVariants() {
        return variants;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDuration(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setVariants(int newSize) {
        variants = newSize;
    }

    public void setID(long ID) {
        this.id = ID;
    }

    public long getID() {
        return id;
    }
}
