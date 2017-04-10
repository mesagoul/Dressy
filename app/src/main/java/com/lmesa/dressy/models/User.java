package com.lmesa.dressy.models;

import android.widget.EditText;

/**
 * Created by Lucas on 08/04/2017.
 */

public class User {
    private String user_pseudo;
    private String user_password;
    private String user_fisrtName;
    private String user_lastName;
    private String user_mail;
    private String user_country;
    private String user_created_at;
    private String user_type;

    public User(String user_fisrtName, String user_lastName, String user_mail, String user_pseudo, String user_country, String user_password) {
        this.user_fisrtName = user_fisrtName;
        this.user_lastName = user_lastName;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_pseudo = user_pseudo;
        this.user_country = user_country;
    }

    public User(String mail, String password) {
        this.user_mail = mail;
        this.user_password = password;
    }


    public String getUser_fisrtName() {
        return user_fisrtName;
    }

    public void setUser_fisrtName(String user_fisrtName) {
        this.user_fisrtName = user_fisrtName;
    }

    public String getUser_lastName() {
        return user_lastName;
    }

    public void setUser_lastName(String user_lastName) {
        this.user_lastName = user_lastName;
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

    public String getUser_pseudo() {
        return user_pseudo;
    }

    public void setUser_pseudo(String user_pseudo) {
        this.user_pseudo = user_pseudo;
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
