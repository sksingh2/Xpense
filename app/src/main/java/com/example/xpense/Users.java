package com.example.xpense;

public class Users {

    private String username;
    private String phone_number;
    private String profile_pic ;

    public Users()
    {

    }


    public Users(String username, String phone_number, String profile_pic) {
        this.username = username;
        this.phone_number = phone_number;
        this.profile_pic = profile_pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
