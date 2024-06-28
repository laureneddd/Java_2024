package com.infosys.dao;

import com.infosys.pojo.Book;
import com.infosys.pojo.User;

public class UserDAO {

    private User[] users = new User[10];
    // BookDAO bookDao = new BookDAO();

    private BookDAO bookDao;

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    // add a new user
    public void add(User newUser) {
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                users[i] = newUser;
                break;
            }
        }
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
    public Book[] addNew(User user, int bookid){

        Book book = this.bookDao.search(bookid);

        Book[] arrayNewbooks = user.getNewBooksArray();
        for(int i = 0; i < arrayNewbooks.length; i++){
            if(arrayNewbooks[i] == null){
                arrayNewbooks[i] = book;
                break;
            }
        }
        user.setNewBooksArray(arrayNewbooks);
        return arrayNewbooks;
    }

    //mark book as favourite
    public Book[] addFavourite(User user, int bookid){

        Book book = this.bookDao.search(bookid);

        Book[] favouriteArry = user.getFavouriteBooks();
        for(int i = 0; i < favouriteArry.length; i++){
            if(favouriteArry[i] == null){
                favouriteArry[i] = book;
                break;
            }
        }
        user.setNewBooksArray(favouriteArry);
        return favouriteArry;
    }

    //mark book as completed
    public Book[] addCompleted(User user, int bookid){

        Book book = this.bookDao.search(bookid);

        Book[] completedArray = user.getCompletedBooks();
        for(int i = 0; i < completedArray.length; i++){
            if(completedArray[i] == null){
                completedArray[i] = book;
                break;
            }
        }
        user.setNewBooksArray(completedArray);
        return completedArray;
    }

}
