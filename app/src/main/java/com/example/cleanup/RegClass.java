package com.example.cleanup;

public class RegClass {
    public String userid,fullname,email,pass;

    public RegClass(String userid, String fullname, String email, String pass) {
        this.userid = userid;
        this.fullname = fullname;
        this.email = email;
        this.pass = pass;
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

    public RegClass(){}
}
