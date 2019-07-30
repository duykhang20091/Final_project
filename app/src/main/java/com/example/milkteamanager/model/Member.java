package com.example.milkteamanager.model;

public class Member {
    private int userid;
    private String username;
    private String fullname;
    private String password_hash;
    private String salt;
    private String created_date;

    public Member(int Userid, String Username, String Fullname, String Password_hash, String Salt, String Created_date) {
        userid = Userid;
        username = Username;
        fullname = Fullname;
        password_hash = Password_hash;
        salt = Salt;
        created_date = Created_date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}