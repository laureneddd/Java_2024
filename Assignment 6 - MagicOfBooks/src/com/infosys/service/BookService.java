package com.infosys.service;

import com.infosys.dao.BookDAO;
import com.infosys.pojo.Book;
import java.util.Scanner;

public class BookService {
    
    private Scanner scanner = new Scanner(System.in);
    // private BookDAO bookDao = new BookDAO();
    // private UserDAO userDao = new UserDAO();

    private BookDAO bookDao;
    
    public BookService(BookDAO bookDAO2){
        this.bookDao = bookDAO2;
    }

    //add a new book
    public void addBook() {
        System.out.println("\nEnter book name: ");
        String bookname = scanner.nextLine();
        System.out.println("\nEnter author name: ");
        String author = scanner.nextLine();
        System.out.println("\nEnter description: ");
        String description = scanner.nextLine();
        System.out.println("\nEnter book id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book addbook = new Book(bookname, author, description, id);

        if(bookDao.add(addbook)) {
            System.out.println("\nYou add a new book.");
        }
        else {
            System.out.println("\nOops, you failed to add a new book.");
        }
    }

    //delete a book with target book id
    public void deleteBook() {
        System.out.println("\nEnter the book id to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        bookDao.delete(id);

        if(bookDao.delete(id)) {
            System.out.println("\nYou delete a book.");
        }
        else {
            System.out.println("\nOops, you failed to delete a book.");
        }
    }

    //get book details with target book id
    public void getBookDetails() {
        System.out.println("\nEnter the book id to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = bookDao.search(id);
        if(book != null){
            System.out.println("\nBook name: " + book.getBookName() + "\nAuthor name: " + book.getAuthorName() + "\nDescription: " + book.getDescription() + "\nBook Id: " + book.getBookId());
        }
        else{
            System.out.println("The book is not found.");
        }
    }

    //display the user's book collections 
    public void displayAllBooks() {
        Book[] books = bookDao.getBooks();
        if(books.length == 0){
            System.out.println("\nYour book list is empty");
        }
        else{
            System.out.println("\nYour all books:");
            for(Book book: books){
                if(book != null){
                    System.out.println(book.toString());
                }
            }
        }
    }


}
