package ge.exen.dto;

import javax.validation.constraints.NotNull;


/**
 * This class is what we expect from user as an input during registration
 */
public class UserRegisterDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String status;
    @NotNull
    private String name;
    @NotNull
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
