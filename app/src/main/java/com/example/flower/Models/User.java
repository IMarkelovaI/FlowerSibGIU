package com.example.flower.Models;

public class User {

    private String login, email, pass;

    public User() {}

    public User(String login, String email, String pass) {
        this.login = login;
        this.email = email;
        this.pass = pass;
    }
    public String getLogin() {
            return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
