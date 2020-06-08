package ge.exen.DAO;

import ge.exen.models.ExamMaterial;

public interface ExamMaterialDao {
    public static final int ERROR = -1;
    public static final int OK = 0;

    int create(ExamMaterial material);
}
