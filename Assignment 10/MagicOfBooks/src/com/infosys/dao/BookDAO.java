package com.infosys.dao;

import com.infosys.pojo.Book;
import java.util.*;

public class BookDAO {

    // Book[] books = new Book[10];
    private List<Book> books = new ArrayList<>();

    //add new book to books array
    public boolean add(String bookname, String authorname, String description, int bookid){
        Book book = new Book(bookname, authorname, description, bookid);
        book.setBookName(bookname);
        book.setAuthorName(authorname);
        book.setDescription(description);
        book.setBookId(bookid);
        return true;
    }

    //delete a book by book id
    public boolean delete(int targetId){
        for(Book book: books){
            if(book != null && book.getBookId() == targetId) {
                book = null;
                return true;
            }
        }
        return false;
    }

    //search a book by book id to get book details
    public Book search(int id){
        for(Book book: books){
            if(book != null && book.getBookId() == id) {
                return book;
            }
        }
        return null;
    }

    //search all books
    public List<Book> getBooks(){
        return books;
    }
}
