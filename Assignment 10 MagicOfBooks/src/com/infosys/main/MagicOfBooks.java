package com.infosys.main;

import com.infosys.dao.BookDAO;
import com.infosys.exception.BookNotFoundException;
import com.infosys.exception.InvalidInputException;
import com.infosys.exception.UserNotFoundException;
import com.infosys.service.BookService;
import com.infosys.service.UserService;
import java.util.Scanner;

public class MagicOfBooks {

    private static BookDAO bookDao = new BookDAO();

    private static BookService bookService = new BookService(bookDao);
    private static UserService userService = new UserService(bookDao);

    private static UserThread userThread = new UserThread();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InvalidInputException {

        MagicOfBooks magicOfBooks = new MagicOfBooks();

        magicOfBooks.mainMenu();
        
    }

    //Menu for display: display all, display new, display favourite, display completed
    public static void displayMenu() throws InvalidInputException, UserNotFoundException, BookNotFoundException{
        while (true) { 
            System.out.println("\n<Display Menu>\nEnter 1 Display All Books\nEnter 2 Display New Books\nEnter 3 Display Favourite Books\nEnter 4 Display Completed Books\nEnter 5 Return User Menu\nEnter 0 Exit");
        String input = scan.nextLine();

        String name = userService.usernameLogIn();

        switch(input){
            case "1":
                bookService.displayAllBooks();
                break;
            case "2":
                userService.displayNewBooks();
                break;
            case "3":
                userService.displayFavBooks();
                break;
            case "4":
                userService.displayCompletedBooks();
                break;
            case "5":
                MagicOfBooks.userMenu();
                break;
            case "0":
                exit();
                break;
            default:
                System.out.println("Your input is invalid.");
        }       
        }

    }

    // Menu for user to register, login, exit
    public static void mainMenu() throws InvalidInputException{
        while (true) { 
            System.out.println("\n<Main Menu>\nEnter 1 Register\nEnter 2 Log In\nEnter 0 Exit");
            String input = scan.nextLine();

            switch(input){
                case "1":
                    userService.register();
                    break;
                case "2":
                    if(userService.login()) {
                        userThread.start();
                        return;
                    }else {
                        MagicOfBooks.mainMenu();
                    }
                    break;
                case "0":
                    exit();
                    break;
                default:
                    System.out.println("Your input is invalid.");
            }
        }

    }


    // Recursive user menu to add, search, display, mark, logout, exit
    public static void userMenu() throws InvalidInputException, UserNotFoundException, BookNotFoundException{
        while (true) { 
            System.out.println("\n<User Menu>\nEnter 1 Add Books\nEnter 2 Get Book Details\nEnter 3 Display Menu\nEnter 4 Mark Books as New\nEnter 5 Mark Books as Favourite\nEnter 6 Mark Books as Completed\nEnter 7 Log out\nEnter 0 Exit");
            String input = scan.nextLine();

            switch(input){
                case "1":
                    bookService.addBook();
                    break;
                case "2":
                    bookService.getBookDetails();
                    break;
                case "3":
                    displayMenu();
                    break;
                case "4":
                    userService.markNew();
                    break;
                case "5":
                    userService.markFavourite();
                    break;
                case "6":
                    userService.markCompleted();
                    break;
                case "7":
                    userService.logout();
                    MagicOfBooks.mainMenu();
                    break;
                case "0":
                    exit();
                    break;
                default:
                    System.out.println("Your input is invalid.");
            }
        }
    }

    //exit the program
    public static void exit() {
        System.out.println("Bye! Enjoy your day!");
        System.exit(0);
    }

}

class UserThread extends Thread {

    public void run() {

        try {
            MagicOfBooks.userMenu();
        } catch (InvalidInputException | UserNotFoundException | BookNotFoundException e) {
            e.printStackTrace();
        } 

    }
}
