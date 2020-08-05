package ge.exen.DAO;

import ge.exen.models.ExamMaterial;

public interface ExamMaterialDao {
   int ERROR = -1;
    int OK = 0;

    /**
     * Adds new material to database.
     * @param material
     * @return
     */
    int create(ExamMaterial material);

    /**
     * Retreives exam material by its ID and variant number.
     * @param id
     * @param var
     * @return
     */
    ExamMaterial get(long id, long var);

    /**
     * Updates link of statement with given exam ID and variant number.
     * @param mat
     */

    void update(ExamMaterial mat);

    /**
     * Removes all the excess statements if number of variants
     * has been reduced.
     * @param id
     * @param newVar
     */
    void remove(long id, long newVar);
}
