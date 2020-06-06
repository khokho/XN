package Objects;

import java.util.Date;
import java.util.List;

public class Exam {
    private String fullName;
    private Date startDate;
    private Integer durationInMinutes;
    private List<String> statementLinks;
    private Integer variants;


    public Exam(String name, Date startDate, int durationInMinutes, int variants, List<String> statementLinks) {
        this.fullName = name;
        this.startDate = startDate;
        this.durationInMinutes = durationInMinutes;
        this.statementLinks = statementLinks;
        this.variants = variants;
        makeConsistent();
    }

    private void makeConsistent() {
        while (statementLinks.size() > variants) {
            statementLinks.remove(statementLinks.size() - 1);
        }

        while (statementLinks.size() < variants) {
            statementLinks.add(null);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public List<String> getStatementLinks() {
        return statementLinks;
    }

    public Integer getVariants() {
        return variants;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDuration(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setStatementLink(String newLink, int index) {
        this.statementLinks.set(index, newLink);
    }

    public void setVariants(int newSize) {
        variants = newSize;
        makeConsistent();
    }
}
