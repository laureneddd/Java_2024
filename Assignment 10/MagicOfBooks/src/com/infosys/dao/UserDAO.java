package com.infosys.dao;

import com.infosys.pojo.Book;
import com.infosys.pojo.User;
import java.util.*;

public class UserDAO {

    // private User[] users = new User[10];
    private List<User> users = new ArrayList<>();
    // BookDAO bookDao = new BookDAO();

    private BookDAO bookDao;

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    // add a new user
    public void add(String username, int id, String email, String password) {
        User user = new User(username, id, email, password);

        user.setUserName(username);
        user.setUserId(id);
        user.setUserEmail(email);
        user.setUserPassword(password);

    }

    //search a user with username and password for login verification
    public boolean searchToLogIn(String username, String password) {
        for(User user: users){
            if(user.getUserName().equals(username) && user.getUserPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //search a user with username and return an user object
    public User searchUserObject(String username) {
        for(User user: users){
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    //delete a user with username
    public void delete(String username) {
        for(User user: users){
            if(user.getUserName().equals(username)){
                user = null;
                break;
            }
        }
    }

    //mark book as new
    public void addNew(User user, int bookid){

        Book book = this.bookDao.search(bookid);
        user.getNewBooks().add(book);
    }

    //mark book as favourite
    public void addFavourite(User user, int bookid){

        Book book = this.bookDao.search(bookid);
        user.getFavouriteBooks().add(book);

    }

    //mark book as completed
    public void addCompleted(User user, int bookid){

        Book book = this.bookDao.search(bookid);
        user.getCompletedBooks().add(book);
    }

}
