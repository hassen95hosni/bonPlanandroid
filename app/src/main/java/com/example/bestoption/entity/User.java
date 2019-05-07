package com.example.bestoption.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("firstname")
    private String name;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("id")
    private int id;

    public User() {
    }


    public User(String email, String password, String name, String lastname, int id) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
