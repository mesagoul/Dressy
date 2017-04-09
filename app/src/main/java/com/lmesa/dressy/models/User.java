package com.lmesa.dressy.models;

/**
 * Created by Lucas on 08/04/2017.
 */

public class User {
    private String user_name;
    private String user_firstname;
    private String user_mail;
    private String user_password;
    private String user_login;
    private String user_created_at;
    private String user_country;

    public User(String user_name, String user_firstname, String user_mail, String user_password, String user_login, String user_created_at, String user_country) {
        this.user_name = user_name;
        this.user_firstname = user_firstname;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_login = user_login;
        this.user_created_at = user_created_at;
        this.user_country = user_country;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_created_at() {
        return user_created_at;
    }

    public void setUser_created_at(String user_created_at) {
        this.user_created_at = user_created_at;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }
}
