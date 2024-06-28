package com.infosys.pojo;

public class User {
    private String userName;
    private int userId;
    private String userEmail;
    private String password;
    private Book[] newBooks;
    private Book[] favouriteBooks;
    private Book[] completedBooks;

    //create an user object
    public User(String username, int id, String email, String password){
        this.userName = username;
        this.userId = id;
        this.userEmail = email;
        this.password = password;
        this.newBooks = new Book[10];
        this.favouriteBooks = new Book[10];
        this.completedBooks = new Book[10];
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
    public Book[] getNewBooksArray(){
        return this.newBooks;
    }

    public void setNewBooksArray(Book[] newbooks){
        this.newBooks = newbooks;
    }

    public Book[] getFavouriteBooks(){
        return this.favouriteBooks;
    }

    public void setFavouriteBooks(Book[] favouritebooks){
        this.favouriteBooks = favouritebooks;
    }

    public Book[] getCompletedBooks(){
        return this.completedBooks;
    }

    public void setCompletedBooks(Book[] completedbooks){
        this.completedBooks = completedbooks;
    }
}