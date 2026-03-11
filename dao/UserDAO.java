package dao;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String FILE_PATH = "data/users.txt";

    public boolean saveUser(User user) {
        FileUtil.writeToFile(FILE_PATH, user.toString(), true);
        return true;
    }

    public User findUserByUsername(String username) {
        List<String> lines = FileUtil.readFromFile(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4 && parts[0].equals(username)) {
                return new User(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            }
        }
        return null;
    }

    public boolean validateLogin(String username, String password) {
        List<String> lines = FileUtil.readFromFile(FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4 && parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUsernameTaken(String username) {
        return findUserByUsername(username) != null;
    }
}