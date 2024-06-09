package com.infosys.service;

import java.util.Scanner;

import com.infosys.dao.BookDAO;
import com.infosys.dao.UserDAO;
import com.infosys.pojo.Book;
import com.infosys.pojo.User;

public class UserService {

    private Scanner scanner = new Scanner(System.in);
    private UserDAO userDao = new UserDAO();
    private BookDAO bookDao;
    private String userLoginName;
    
    public UserService(BookDAO bookDao) {
        this.bookDao = bookDao;
        this.userDao.setBookDao(bookDao);
    }


    public void register() {
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

        User newUser = new User(name, id, email, password);

        userDao.add(newUser);
    }

    //user log in by searching and checking username and password in user arrays
    public boolean login() {
        System.out.println("<Log In> Enter your username: ");
        String name = scanner.nextLine();
        userLoginName = name;
        System.out.println("<Log In> Enter your password: ");
        String password = scanner.nextLine();

        if(userDao.searchToLogIn(name, password)){
            System.out.println("You have logged in.");
            return true;
        }
        else{
            System.out.println("You failed to log in.");
            return false;
        }
    }

    //user log out
    public void logout() {
        String name = userLoginName;
        userDao.delete(name);
        System.out.println("You have logged out.");
    }


    //mark book as 'new' and return new books array in user object
    public void markNew(){

        String name = userLoginName;

        System.out.println("\n<Mark New> Enter book id you wanna mark: ");
        int bookid = scanner.nextInt();
        scanner.nextLine();

        User user = userDao.searchUserObject(name);
        Book[] newBooks = userDao.addNew(user, bookid);
        for(int i = 0; i < newBooks.length; i++){
            if(newBooks[i] != null){
                System.out.println(newBooks[i].toString());
            }
        }

        System.out.println("Book marked as new.");

    }

     //mark book as 'favourite' and return favourite books array in user object
     public void  markFavourite(){
        String name = userLoginName;

        System.out.println("\n<Mark Favourite> Enter book id you wanna mark: ");
        int bookid = scanner.nextInt();
        scanner.nextLine();

        User user = userDao.searchUserObject(name);
        Book[] newBooks = userDao.addFavourite(user, bookid);
        for(int i = 0; i < newBooks.length; i++){
            if(newBooks[i] != null){
                System.out.println(newBooks[i].toString());
            }
        }
        System.out.println("Book marked as favourite.");
    }   

    //mark book as 'completed' and return completed books array in user object
    public void markCompleted(){
        String name = userLoginName;

        System.out.println("\n<Mark Completed> Enter book id you wanna mark: ");
        int bookid = scanner.nextInt();
        scanner.nextLine();

        User user = userDao.searchUserObject(name);
        Book[] newBooks = userDao.addCompleted(user, bookid);
        for(int i = 0; i < newBooks.length; i++){
            if(newBooks[i] != null){
                System.out.println(newBooks[i].toString());
            }
        }
        System.out.println("Book marked as favourite.");
    }

    //display array marked as 'new'
    public void displayNewBooks(){
        System.out.println("Your new books:");

        String name = userLoginName;

        User user = userDao.searchUserObject(name);
        Book[] newBooks = user.getNewBooksArray();
        for(int i = 0; i < user.getNewBooksArray().length; i++){
            if(newBooks[i] != null){
                System.out.println(newBooks[i].toString());
            }
        }
    } 
    
    //display array marked as 'favourite'
    public void displayFavBooks(){
        System.out.println("Your favourite books:");

        User user = userDao.searchUserObject(userLoginName);
        Book[] books = user.getFavouriteBooks();
        for(Book book: books){
            if(book != null){
                System.out.println(book);
            }
        }
    } 

    //display array marked as 'completed'
    public void displayCompletedBooks(){
        System.out.println("Your completed books:");

        User user = userDao.searchUserObject(userLoginName);
        Book[] books = user.getCompletedBooks();
        for(Book book: books){
            if(book != null){
                System.out.println(book);
            }
        }
    } 

    //return username for login to main method
    public String usernameLogIn(){
        return userLoginName;
    }

}
