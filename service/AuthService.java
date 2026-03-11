package service;

import dao.UserDAO;
import model.User;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String username, String password) {
        return userDAO.validateLogin(username, password);
    }

    public boolean register(String username, String password, String name, int age) {
        if (userDAO.isUsernameTaken(username)) {
            return false;
        }
        User user = new User(username, password, name, age);
        return userDAO.saveUser(user);
    }

    public User recoverPassword(String username, String name, int age) {
        User user = userDAO.findUserByUsername(username);
        if (user != null && user.getName().equals(name) && user.getAge() == age) {
            return user;
        }
        return null;
    }
}