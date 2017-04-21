package com.lmesa.dressy.models;

import java.util.ArrayList;

/**
 * Created by Lucas on 15/04/2017.
 */

public class ListClothes {
    private ArrayList<Clothes> listClothes;
    private int size;

    public ListClothes(ArrayList<Clothes> clothes, int size) {
        this.listClothes = clothes;
        this.size = size;
    }

    public ArrayList<Clothes> getClothes() {
        return listClothes;
    }

    public void setClothes(ArrayList<Clothes> clothes) {
        this.listClothes = clothes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
