package com.example.team;

import com.google.gson.annotations.SerializedName;

public class User {
    private Integer id, locationId, access;

    @SerializedName("designation")
    private String role;

    private String password, qualification, email, name;


    public User(Integer id, String name, String role, String password, String qualification, int locationId, int access, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        this.qualification = qualification;
        this.locationId = locationId;
        this.access = access;
        this.email = email;
    }



    public Integer getId() {
        return id;
    }

    public String getName() { return name; }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getQualification() { return qualification; }

    public int getLocationId() { return locationId; }

    public int getAccess() { return access; }

    public String getEmail() { return email; }


}
