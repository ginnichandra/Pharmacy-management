package com.example.team;

import com.google.gson.annotations.SerializedName;

public class Jwt {

@SerializedName("jwt")
private String jwt;

    public Jwt(String token) {
        this.jwt = token;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
