package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.dto.UserUpdateDTO;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder hashService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private QueueService queueService;

    private final static String userAttrName = "user";

    @Override
    public boolean checkMailExists(final String email) {
        return userDAO.getUserByMail(email) != null;
    }

    @Override
    public boolean registerNewUser(final UserRegisterDTO userDto) {
        //TODO check proper status
        if (checkMailExists(userDto.getEmail())) return false;
        //TODO check other stuff(password maybe
        final User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(hashService.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setStatus(userDto.getStatus());
        userDAO.create(user);
        return user.getId() != -1;
    }


    @Override
    public boolean login(final UserLoginDTO loginDTO) {
        User user = userDAO.getUserByMail(loginDTO.getEmail());
        if (user == null) return false;
        if (!checkPassword(user, loginDTO.getPassword())) return false;
        httpSession.setAttribute(userAttrName, user);
        queueService.createDisabledStates();
        return true;
    }

    @Override
    public User getCurrentUser() {
        return (User) httpSession.getAttribute(userAttrName);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(userAttrName);
    }


    @Override
    public boolean updateUser(UserUpdateDTO updateDTO) {
        User user = userDAO.getUserByMail(updateDTO.getOldEmail());
        if (user == null) return false;
        if (!checkPassword(user, updateDTO.getOldPassword())) return false;
        if (updateDTO.getEmail() != null)
            user.setEmail(updateDTO.getEmail());
        if (updateDTO.getPassword() != null)
            user.setPasswordHash(hashService.encode(updateDTO.getPassword()));
        if (updateDTO.getName() != null)
            user.setName(updateDTO.getName());
        if (updateDTO.getLastName() != null)
            user.setLastName(updateDTO.getLastName());
        if (updateDTO.getStatus() != null)
            user.setStatus(updateDTO.getStatus());
        userDAO.updateRowById(user);
        return user.getId() != -1;
    }

    @Override
    public boolean removeUser() {
        User cur = getCurrentUser();
        if (cur == null) return false;
        userDAO.removeUserById(cur.getId());
        httpSession.removeAttribute(userAttrName);
        return true;
    }


    /**
     * utility function
     *
     * @param toCheck  user to check
     * @param password password
     * @return true is correct password
     */
    private boolean checkPassword(User toCheck, String password) {
        return hashService.matches(password, toCheck.getPasswordHash());
    }

}
