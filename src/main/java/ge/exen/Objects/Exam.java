package ge.exen.Objects;

import java.util.Date;
import java.util.List;

public class Exam {
    private String fullName;
    private String startDate;
    private Integer durationInMinutes;
    private List<String> statementLinks;
    private Integer variants;
    private long db_ID;


    public Exam(String name, String startDate, int durationInMinutes, int variants, List<String> statementLinks) {
        this.fullName = name;
        this.startDate = startDate;
        this.durationInMinutes = durationInMinutes;
        this.statementLinks = statementLinks;
        this.variants = variants;
        makeConsistent();
    }

    private void makeConsistent() {
        if (statementLinks == null) return;
        while (statementLinks.size() > variants + 1) {
            statementLinks.remove(statementLinks.size() - 1);
        }

        while (statementLinks.size() < variants) {
            statementLinks.add(null);
        }
    }

    public void setStatementLinks(List<String> statementLinks) {
        this.statementLinks = statementLinks;
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

    public List<String> getStatementLinks() {
        return statementLinks;
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

    public void setStatementLink(String newLink, int index) {
        this.statementLinks.set(index, newLink);
    }

    public void setVariants(int newSize) {
        variants = newSize;
        makeConsistent();
    }

    public void setID(long ID) {
        this.db_ID = ID;
    }

    public long getID() {
        return db_ID;
    }
}
