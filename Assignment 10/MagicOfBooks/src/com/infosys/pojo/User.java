package com.infosys.pojo;

import java.util.*;

public class User {
    private String userName;
    private int userId;
    private String userEmail;
    private String password;
    private List<Book> newBooks;
    private List<Book> favouriteBooks;
    private List<Book> completedBooks;

    //create an user object
    public User(String username, int id, String email, String password){
        this.userName = username;
        this.userId = id;
        this.userEmail = email;
        this.password = password;
        this.newBooks = new ArrayList();
        this.favouriteBooks = new ArrayList();
        this.completedBooks = new ArrayList();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String username) {
        this.userName  = username;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userid) {
        this.userId  = userid;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String useremail) {
        this.userEmail  = useremail;
    }

    public String getUserPassword() {
        return this.password;
    }

    public void setUserPassword(String password) {
        this.password  = password;
    }
    public List<Book> getNewBooks(){
        return this.newBooks;
    }

    public void setNewBooks(List<Book> newbooks){
        this.newBooks = newbooks;
    }

    public List<Book> getFavouriteBooks(){
        return this.favouriteBooks;
    }

    public void setFavouriteBooks(List<Book> favouritebooks){
        this.favouriteBooks = favouritebooks;
    }

    public List<Book> getCompletedBooks(){
        return this.completedBooks;
    }

    public void setCompletedBooks(List<Book> completedbooks){
        this.completedBooks = completedbooks;
    }
}