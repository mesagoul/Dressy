package com.lmesa.dressy.models;

import java.util.ArrayList;

/**
 * Created by Lucas on 15/04/2017.
 */

public class ListClothes {
    private ArrayList<Clothes> clothes;
    private int size;

    public ListClothes(ArrayList<Clothes> clothes, int size) {
        this.clothes = clothes;
        this.size = size;
    }

    public ArrayList<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(ArrayList<Clothes> clothes) {
        this.clothes = clothes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
