package com.example.team;

public class Details {

    private String email;
    private String password;

    public Details(String email, String passowrd) {
        this.email = email;
        this.password = passowrd;
    }

    public String getEmail() {
        return email;
    }

    public String getPassowrd() {
        return password;
    }
}
