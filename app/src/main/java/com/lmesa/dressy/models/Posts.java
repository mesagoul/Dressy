package com.lmesa.dressy.models;

import java.util.ArrayList;

/**
 * Created by Lucas on 18/04/2017.
 */

public class Posts {
    private int size;
    private ArrayList<Post> posts;

    public Posts(int size, ArrayList<Post> posts) {
        this.size = size;
        this.posts = posts;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
