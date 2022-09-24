package com.example.cleanup.model;

import java.util.ArrayList;

public class UserModel {
    public String userid,fullname,email,pass;

    public UserModel(String userid, String fullname, String email, String pass, boolean verified, ArrayList<String> rooms) {
        this.userid = userid;
        this.fullname = fullname;
        this.email = email;
        this.pass = pass;
        this.verified = verified;
        this.rooms = rooms;
    }

    public boolean verified;
    public ArrayList<String> rooms;

    public UserModel(){}
}
