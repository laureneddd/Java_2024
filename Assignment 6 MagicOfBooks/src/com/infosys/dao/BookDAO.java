package com.infosys.dao;

import com.infosys.pojo.Book;

public class BookDAO {

    Book[] books = new Book[10];

    //add new book to books array
    public boolean add(Book newbook){
        for(int i = 0; i < books.length; i++) {
            if(books[i] == null){
                books[i] = newbook;
                break;
            }
        }
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
    public Book[] getBooks(){
        return this.books;
    }
}
