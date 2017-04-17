package com.lmesa.dressy.models;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Post {
    private String username;
    private String title;
    private String desc;
    private Clothes clothes;

    public Post(String username, String title, String desc, Clothes clothes) {
        this.username = username;
        this.title = title;
        this.desc = desc;
        this.clothes = clothes;
    }

    public Post(String title, String desc, Clothes clothes) {
        this.title = title;
        this.desc = desc;
        this.clothes = clothes;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
