package ge.exen.dto;

public class ExamDTO {
//    @NotNull
    private Integer variants;
//    @NotNull
    private String fullName;
//    @NotNull
    private String startDate;
//    @NotNull
    private Integer hours;
//    @NotNull
    private Integer minutes;

    public Integer getVariants() {
        return variants;
    }

    public void setVariants(Integer variants) {
        this.variants = variants;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStartDate() {
        return startDate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    /**
     * Set start date with format 'yyyy-MM-dd hh:mm'
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate.replace('-', '/');
    }

}
