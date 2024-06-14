package com.infosys.pojo;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String role; //choose client or visitor

    public User(){

    }

    public User(String role){
        this.role = role;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public String getLastName(){
        return this.lastName;
    }   

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String username){
        this.userName = username;
    }

    public String getPassWord(){
        return this.passWord;
    }

    public void setPassWord(String password){
        this.passWord = password;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
