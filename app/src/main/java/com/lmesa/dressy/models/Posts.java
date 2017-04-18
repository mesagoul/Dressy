package com.lmesa.dressy.models;

import java.util.ArrayList;

/**
 * Created by Lucas on 18/04/2017.
 */

public class Posts {
    private int size;
    private ArrayList<Post> listPost;

    public Posts(int size, ArrayList<Post> listPost) {
        this.size = size;
        this.listPost = listPost;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Post> getListPost() {
        return listPost;
    }

    public void setListPost(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }
}
