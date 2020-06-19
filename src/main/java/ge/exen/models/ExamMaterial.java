package ge.exen.models;


public class ExamMaterial {

  private long materialId;
  private String materialLink;
  private long var;
  private long examId;

  public ExamMaterial(String link, long var, long examId) {
    this.materialLink = link;
    this.var = var;
    this.examId = examId;
  }

    public ExamMaterial() {

    }


    public long getMaterialId() {
    return materialId;
  }

  public void setMaterialId(long materialId) {
    this.materialId = materialId;
  }


  public String getMaterialLink() {
    return materialLink;
  }

  public void setMaterialLink(String materialLink) {
    this.materialLink = materialLink;
  }


  public long getVar() {
    return var;
  }

  public void setVar(long var) {
    this.var = var;
  }


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }

}
