package ge.exen.models;


public class ExamMaterials {

  private long materialId;
  private long materialLink;
  private long var;
  private long examId;


  public long getMaterialId() {
    return materialId;
  }

  public void setMaterialId(long materialId) {
    this.materialId = materialId;
  }


  public long getMaterialLink() {
    return materialLink;
  }

  public void setMaterialLink(long materialLink) {
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
