package pl.coderslab.main.domain;


import org.mindrot.jbcrypt.BCrypt;

public class User {  //POJO


    private int id;
    private String userName;
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this.id = 0;
        this.userName = userName;
        this.email = email;
        this.setHashedPassword(password);
    }

    public User() {
    }

    private void setHashedPassword(String password) {
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
}