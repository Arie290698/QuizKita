package com.ilkom.quizkita.Model;

public class UserModel {
    public String fname, email, jk, hp, alamat;

    public UserModel(){

    }

    public UserModel(String name, String email) {
        this.fname = name;
        this.email = email;
    }
}