package com.infosys.dao;
import com.infosys.pojo.User;
import java.util.*;

public class UserDAO {

    private List<User> users = new ArrayList<>();

    public void add(String first, String last, String username, String password) {
        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setUserName(username);
        user.setPassWord(password);
        users.add(user);
    }

    public boolean search(String username, String pass) {
        boolean found = false;
        for (User user: users) { 
            if (user.getUserName().equals(username) && user.getPassWord().equals(pass)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean update(String originalName, String newUserName) {
        boolean update = false;
        for (User user: users) {
            if (user.getUserName().equals(originalName)) {
                user.setUserName(newUserName);
                update = true;
            }
        }
        return update;
    }

    public void delete(String username) {
        for (User user: users) {
            if (user.getUserName().equals(username)) {
                users.remove(user);
            }
        }
    }

    // search username in array, and mark role as 'Visitor'
    public boolean roleVisitor(String username) {
        boolean found = false;
        for (User user: users) {
            if (user.getUserName().equals(username)) {
                user.setRole("Visitor");
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean roleClient(String username) {
        boolean found = false;
        for (User user: users) {
            if (user.getUserName().equals(username)) {
                user.setRole("Client");
                found = true;
                break;
            }
        }
        return found;
    }

    public List<User> getUsers() {
        return users;
    }
}
