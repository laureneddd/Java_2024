package com.infosys.service;

import java.util.Scanner;

import com.infosys.dao.UserDAO;
import com.infosys.exception.ArrayFullException;
import com.infosys.exception.StringReadException;
import com.infosys.exception.ValidationLoginException;

public class UserService {
    Scanner scanner;
    UserDAO userDao;
    private String userLogName;
    private String visitorLog;

    public UserService(){
        scanner = new Scanner(System.in);
        userDao = new UserDAO();
    }

    // login for visitor
    public void visitorLog() throws StringReadException{

        boolean logged = false;

        while (!logged) {
            try {
                System.out.println("\n<Log In> Enter Visitor Name: ");
                String visitorName = scanner.nextLine().trim();
    
                if (visitorName.isEmpty()) {
                    throw new StringReadException("Your name cannot be empty.");
                } else {
                    userDao.roleVisitor(visitorName);
                    System.out.println("You have been successfully logged in as a Visitor.");
                    logged = true; 
                } 
            } catch (StringReadException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        }

    //login for client
    public void clientLog() throws ValidationLoginException, StringReadException{
        boolean logged = false;

        while (!logged) {
            try {
                System.out.println("\n<Log In> Enter Username: ");
                String username = scanner.nextLine().trim();
                System.out.println("\n<Log In> Enter Password: ");
                String password = scanner.nextLine().trim();
    
                if (username.isEmpty() || password.isEmpty()) {
                    throw new StringReadException("Your name or password cannot be empty.");
                }
    
                if (userDao.search(username, password)) {
                    userDao.roleClient(username);
                    System.out.println("You have been successfully logged in as a Client.");
                    logged = true; 
                } else {
                    throw new ValidationLoginException("You failed to log in as a Client.");
                }
            } catch (StringReadException | ValidationLoginException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //client to log out
    public void logOut(){
        // if(!userDao.roleVisitor(userLogName)){
        //     System.out.println("Your name cannot be found.");
        // } else {
            userLogName = null;
            System.out.println("You have been successfully logged out.");
        // }
    }

    //add an user
    public void add() throws StringReadException, ArrayFullException{

        System.out.println("\n<Add User> Enter First Name: ");
        String first = scanner.nextLine();
        System.out.println("\n<Add User> Enter Last Name: ");
        String last = scanner.nextLine();
        System.out.println("\n<Add User> Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("\n<Add User> Enter Password: ");
        String password = scanner.nextLine();

        if(first.isEmpty() || last.isEmpty() || username.isEmpty() || password.isEmpty()) {
            throw new StringReadException("Your name or password cannot be empty.");
        } else {
            userDao.add(first, last, username, password);
        } 
    }

    //delete an user account
    public void delete(){
        System.out.println("\n<Register> Enter the username to delete this account: ");
        String userDel = scanner.nextLine();
        userDao.delete(userDel);
        userLogName = null;
        System.out.println("\nThis account has been deleted.");
    }

    public void register() throws StringReadException{
        boolean registered = false;

        while (!registered) {
            try {
                System.out.println("\nPlease register first. <Register> Enter First Name: ");
                String firstnm = scanner.nextLine().trim();
                System.out.println("\n<Register> Enter Last Name: ");
                String lastnm = scanner.nextLine().trim();
                System.out.println("\n<Register> Enter User Name: ");
                String usernm = scanner.nextLine().trim();
                System.out.println("\n<Register> Enter Password: ");
                String pass = scanner.nextLine().trim();

                if (firstnm.isEmpty() || lastnm.isEmpty() || usernm.isEmpty() || pass.isEmpty()) {
                    throw new StringReadException("Your name or password cannot be empty.");
                } else {
                    userDao.add(firstnm, lastnm, usernm, pass);
                    System.out.println("\n" + firstnm + ", you have been successfully registered.");
                    registered = true; 
                }
            } catch (StringReadException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public String checkAssign(){
        return visitorLog;
    }

    public void updateUserName() throws StringReadException{
        System.out.println("\nEnter your new username: ");
        String newname = scanner.nextLine(); 
        userDao.update(userLogName, newname);

        if(newname.isEmpty()){
            throw new StringReadException("Your new name cannot be empty.");
        } else {
            System.out.println("\nYour username has been changed.");
        }

    }

    public String visitorName(){
        return this.visitorLog;
    }

    public String clientName(){
        return this.userLogName;
    }
}
