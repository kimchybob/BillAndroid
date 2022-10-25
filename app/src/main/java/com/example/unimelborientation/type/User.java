package com.example.unimelborientation.type;

public class User {
    private String uid;
    private String uname;
    private String email;
    private String mobile;

    public User(String uid, String uname, String email, String mobile) {
        this.uid = uid;
        this.uname = uname;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
