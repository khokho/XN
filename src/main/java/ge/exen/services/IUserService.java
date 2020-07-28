package ge.exen.services;

import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.dto.UserUpdateDTO;
import ge.exen.models.User;

public interface IUserService {

    /**
     * @param email email to check
     * @return true if exists
     */
    boolean checkMailExists(String email);

    /**
     * @param userDto User input for registration
     * @return true if successfully added
     * creates new user in DB
     * DOES NOT automatically log in
     */
    boolean registerNewUser(UserRegisterDTO userDto);

    /**
     * @param loginDTO login into user
     * @return true is successful
     */
    boolean login(UserLoginDTO loginDTO);

    /**
     * @return returns current user if exists
     * null otherwise
     */
    User getCurrentUser();

    /**
     * removes user attribute from session
     */
    void logout();

    /**
     * @param updateDTO user settings update parameters
     * @return true is successful
     */
    boolean updateUser(UserUpdateDTO updateDTO);


    /**
     * removes current user and logs out
     * @return true for success
     */
    boolean removeUser();
}
