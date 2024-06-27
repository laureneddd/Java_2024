package com.infosys.pojo;

public class Book {
    private String bookName;
    private String authorName;
    private String description;
    private int bookId;

    public Book(String bookname, String authorname, String description, int bookid ) {
        this.bookName = bookname;
        this.authorName = authorname;
        this.description = description;
        this.bookId = bookid;
    }


    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookname){
        this.bookName  = bookname;
    }

    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorname){
        this.authorName  = authorname;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description  = description;
    }

    public int getBookId(){
        return this.bookId;
    }

    public void setBookId(int bookid){
        this.bookId  = bookid;
    }

    @Override
    public String toString(){
       return "Books: " + bookName + authorName + description + bookId;
    }

}
