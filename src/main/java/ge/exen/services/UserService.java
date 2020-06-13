package ge.exen.services;

import ge.exen.DAO.UserDAO;
import ge.exen.DAO.UserSQLDAO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder hashService;

    /**
     * @param userDto User input for registration
     * @return new User entity which was added to db
     * creates new user in DB
     * id is -1 if unsucessful
     */
    public User registerNewUser(final UserRegisterDTO userDto){
        //TODO check email
        //TODO check other stuff(password maybe)
        final User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(hashService.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setStatus(userDto.getStatus());
        userDAO.create(user);
        return user;
    }

}
