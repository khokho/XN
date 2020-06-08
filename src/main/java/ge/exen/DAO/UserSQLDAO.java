package ge.exen.DAO;

import ge.exen.models.User;

import javax.persistence.SqlResultSetMapping;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSQLDAO extends AbstractSQLDAO implements UserDAO {
    @Override
    public void create(User user) {
        try {
            PreparedStatement st = db.getConnection().prepareStatement("INSERT INTO user (Email,password_hash,status,name,last_name)  VALUES(?,?,?,?,?);");
            st.setString(1, user.getEmail());
            st.setString(2, user.getPasswordHash());
            st.setString(3, user.getStatus());
            st.setString(4, user.getName());
            st.setString(5, user.getLastName());
            if (st.executeUpdate() == 0) throw new SQLException("something went wrong while inserting an user");
            ResultSet generatedKeys = st.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);
            user.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            user.setId(-1);
        }
    }

    @Override
    public User getUser(Long userId) {
        try {
            PreparedStatement st = db.getConnection().prepareStatement("SELECT* FROM user WHERE Id = ? ;");
            st.setLong(1, userId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.isLast()) throw new SQLException("user with id = " + userId + "doesNotexist");
            resultSet.next();
            return parseToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param resultSet result set
     * @return user with resultSet info
     */
    private User parseToUser(ResultSet resultSet) {
        try {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setEmail(resultSet.getString(2));
            user.setPasswordHash(resultSet.getString(3));
            user.setStatus(resultSet.getString(4));
            user.setName(resultSet.getString(5));
            user.setLastName(resultSet.getString(6));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getUsersByStatus(String status) {
        try {
            PreparedStatement st = db.getConnection().prepareStatement("SELECT* FROM user WHERE status = ?;");
            st.setString(1, status);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.isLast()) throw new SQLException("no one is in database with that kind of status");
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(parseToUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
