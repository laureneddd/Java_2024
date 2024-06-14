package com.infosys.dao;
import com.infosys.pojo.User;
import java.util.Arrays;

public class UserDAO {
    private User[] users = new User[5];
    private int size = 0; 

    public User[] add(String first, String last, String username, String password) {
        if (size >= users.length) {
            users = Arrays.copyOf(users, users.length + 1); 
        }
        users[size] = new User();
        users[size].setFirstName(first);
        users[size].setLastName(username);
        users[size].setUserName(username);
        users[size].setPassWord(password);
        size++; 
        return users;
    }

    public boolean search(String username, String pass) {
        boolean found = false;
        for (int i = 0; i < size; i++) { 
            if (users[i].getUserName().equals(username) && users[i].getPassWord().equals(pass)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean update(String originalName, String newUserName) {
        boolean update = false;
        for (int i = 0; i < size; i++) {
            if (users[i].getUserName().equals(originalName)) {
                users[i].setUserName(newUserName);
                update = true;
            }
        }
        return update;
    }

    public User[] delete(String username) {
        for (int i = 0; i < size; i++) {
            if (users[i].getUserName().equals(username)) {
                for (int j = i; j < size - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[size - 1] = null; 
                size--; 
                return users;
            }
        }
        System.out.println("User " + username + " not found.");
        return users;
    }

    // search username in array, and set role as 'Visitor'
    public boolean roleVisitor(String username) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (users[i].getUserName().equals(username)) {
                users[i].setRole("Visitor");
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean roleClient(String username) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (users[i].getUserName().equals(username)) {
                users[i].setRole("Client");
                found = true;
                break;
            }
        }
        return found;
    }

    public User[] getUsers() {
        return users;
    }
}
