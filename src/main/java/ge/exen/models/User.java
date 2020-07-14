package ge.exen.models;


public class User {

  public static String LECTURER = "lector";
  public static String STUDENT = "student";
  public static String ADMIN = "admin";

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", passwordHash='" + passwordHash + '\'' +
            ", status='" + status + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  private long id;
  private String email;
  private String passwordHash;
  private String status;
  private String name;
  private String lastName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    if(status==null){
      this.status=null;
      return;
    }
    switch (status) {
      case "student":
        this.status = STUDENT;
        break;
      case "lector":
        this.status = LECTURER;
        break;
      case "admin":
        this.status = ADMIN;
        break;
      default:
        this.status = null;
    }
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

}
