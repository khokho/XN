package ge.exen.dto;

import org.springframework.web.bind.annotation.RequestParam;

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

    public void setVariants(Integer variants) {
        this.variants = variants.toString();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * Set start date with format 'yyyy-MM-dd hh:mm'
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setHours(Integer hours) {
        this.hours = hours.toString();
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes.toString();
    }
}
