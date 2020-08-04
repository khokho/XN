package ge.exen.dto;

import javax.validation.constraints.NotNull;

public class ExamDTO {
    @NotNull
    private String variants;
    @NotNull
    private String fullName;
    @NotNull
    private String startDate;
    @NotNull
    private String hours;
    @NotNull
    private String minutes;

    public Integer getVariants() {
        return Integer.parseInt(variants);
    }

    public String getFullName() {
        return fullName;
    }

    public String getStartDate() {
        return startDate;
    }

    public Integer getHours() {
        return Integer.parseInt(hours);
    }

    public Integer getMinutes() {
        return Integer.parseInt(minutes);
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Set start date with format 'yyyy-MM-dd hh:mm'
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate.replace('-', '/');
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
