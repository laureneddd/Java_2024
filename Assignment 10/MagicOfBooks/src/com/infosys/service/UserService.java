package com.infosys.service;

import com.infosys.dao.BookDAO;
import com.infosys.dao.UserDAO;
import com.infosys.exception.BookNotFoundException;
import com.infosys.exception.InvalidInputException;
import com.infosys.exception.UserNotFoundException;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;
import java.util.Scanner;

public class UserService {

    private Scanner scanner = new Scanner(System.in);
    private UserDAO userDao = new UserDAO();
    private BookDAO bookDao;
    private String userLoginName;
    
    public UserService(BookDAO bookDao) {
        this.bookDao = bookDao;
        this.userDao.setBookDao(bookDao);
    }


    public void register() throws InvalidInputException {

        System.out.println("\n<Register> Enter your name: ");
        String name = scanner.nextLine();

        int id;
        System.out.println("\n<Register> Enter your id: ");
        while (true) {    
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); 
                break; 
            } else {
                System.out.println("\n<Register> Your input is invalid. Enter a valid id:");
                scanner.nextLine(); 
            }
        }

        System.out.println("\n<Register> Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("\n<Register> Enter your password: ");
        String password = scanner.nextLine();

        userDao.add(name, id, email, password);
        
    }

    //user log in by searching and checking username and password in user arrays
    public boolean login() {
        try {
            System.out.println("<Log In> Enter your username: ");
            String name = scanner.nextLine();
            userLoginName = name;
            System.out.println("<Log In> Enter your password: ");
            String password = scanner.nextLine();

            if (userDao.searchToLogIn(name, password)) {
                System.out.println("You have logged in.");
                return true;
            } else {
                throw new UserNotFoundException("Invalid username or password.");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            return false;
        }
    }

    //user log out
    public void logout() {
        try {
            userLoginName = null;
            System.out.println("You have logged out.");
        } catch (Exception e) {
            System.out.println("An error occurred during logout: " + e.getMessage());
        }
    }


    //mark book as 'new' and return new books array in user object
    public void markNew(){

        try {
            System.out.println("\n<Mark New> Enter book id you want to mark: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            User user = userDao.searchUserObject(userLoginName);
            if (user == null) {
                throw new UserNotFoundException("User not found.");
            }

            Book book = bookDao.search(bookId);
            if (book == null) {
                throw new BookNotFoundException("Book not found.");
            }

            userDao.addNew(user, bookId);
            System.out.println("Book: " + bookId + " marked as new.");
        } catch (UserNotFoundException | BookNotFoundException  e) {
            System.out.println(e.getMessage());
        }
    }

     //mark book as 'favourite' and return favourite books array in user object
     public void  markFavourite(){

        try {
            System.out.println("\n<Mark Favourite> Enter book id you want to mark: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            User user = userDao.searchUserObject(userLoginName);
            if (user == null) {
                throw new UserNotFoundException("User not found.");
            }

            Book book = bookDao.search(bookId);
            if (book == null) {
                throw new BookNotFoundException("Book not found.");
            }

            userDao.addFavourite(user, bookId);
            System.out.println("Book: " + bookId + " marked as favourite.");
        } catch (UserNotFoundException | BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }   

    //mark book as 'completed' and return completed books array in user object
    public void markCompleted(){

        try {
            System.out.println("\n<Mark Completed> Enter book id you want to mark: ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            User user = userDao.searchUserObject(userLoginName);
            if (user == null) {
                throw new UserNotFoundException("User not found.");
            }

            Book book = bookDao.search(bookId);
            if (book == null) {
                throw new BookNotFoundException("Book not found.");
            }

            userDao.addCompleted(user, bookId);
            System.out.println("Book: " + bookId + " marked as completed.");
        } catch (UserNotFoundException | BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        
    }

    //display array marked as 'new'
    public void displayNewBooks() throws UserNotFoundException{
        System.out.println("Your new books:");

        String name = userLoginName;

        User user = userDao.searchUserObject(name);
        for(Book book: user.getNewBooks()){
            System.out.println(book);
        }
    } 
    
    //display array marked as 'favourite'
    public void displayFavBooks() throws UserNotFoundException{
        System.out.println("Your favourite books:");

        User user = userDao.searchUserObject(userLoginName);
        for(Book book: user.getFavouriteBooks()){
            System.out.println(book);
        }
    } 

    //display array marked as 'completed'
    public void displayCompletedBooks() throws UserNotFoundException{
        System.out.println("Your completed books:");

        User user = userDao.searchUserObject(userLoginName);
        for(Book book: user.getCompletedBooks()){
                System.out.println(book);
        }
    } 

    //return username for login to main method
    public String usernameLogIn(){
        return userLoginName;
    }

}
