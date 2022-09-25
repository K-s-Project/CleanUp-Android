package com.example.cleanup.model;

import java.util.ArrayList;

public class UserModel {
    public String userid,fullname,email,pass;
    public boolean verified;
    public ArrayList<String> rooms;

    public UserModel(String userid, String fullname, String email, String pass, boolean verified, ArrayList<String> rooms) {
        this.userid = userid;
        this.fullname = fullname;
        this.email = email;
        this.pass = pass;
        this.verified = verified;
        this.rooms = rooms;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public ArrayList<String> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<String> rooms) {
        this.rooms = rooms;
    }

    public UserModel(){}
}
