package com.lmesa.dressy.models;

/**
 * Created by Lucas on 08/04/2017.
 */

public class Post {
    private String username;
    private String title;
    private String desc;
    private String urlImage;
    private Integer score;

    public Post(String username, String title, String desc, String urlImage, Integer score) {
        this.username = username;
        this.title = title;
        this.desc = desc;
        this.urlImage = urlImage;
        this.score = score;
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

    public String getUrlImage() {
        return urlImage;
    }

    public Integer getScore() {
        return score;
    }
}
