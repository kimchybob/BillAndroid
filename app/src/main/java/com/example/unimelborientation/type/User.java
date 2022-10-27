package com.example.unimelborientation.type;

public class User {
    private String uid;
    private String uname;
    private String email;
    private String mobile;
    private int unit;
    private String landlord;

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public User(String uid, String uname, String email, String mobile, int unit, String landlord) {
        this.uid = uid;
        this.uname = uname;
        this.email = email;
        this.mobile = mobile;
        this.unit = unit;
        this.landlord = landlord;
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
