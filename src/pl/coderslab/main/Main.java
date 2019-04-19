package pl.coderslab.main;

import pl.coderslab.main.dao.UserDao;
import pl.coderslab.main.domain.User;

public class Main {

    public static void main(String[] args) {
        // write your code here

        User user= new User("name","email@email.com","passw");
        UserDao dao = new UserDao();
        dao.create(user);
    }
}
