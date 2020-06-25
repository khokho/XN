package ge.exen.DAO;

import ge.exen.models.User;

import java.util.List;

public interface UserDAO {
    /**
     * @param user adds user to database and sets the id
     */
    void create(User user);

    /**
     * @param userId specified id
     * @return user by id
     */
    User getUser(Long userId);

    /**
     * @param email mail of user
     * @return User if fount else null
     */
    User getUserByMail(String email);

    /**
     * @param status specified status
     * @return List of users with specified status
     */
    List<User> getUsersByStatus(String status);


}
