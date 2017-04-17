package com.lmesa.dressy.interfaces;

/**
 * Created by Lucas on 15/04/2017.
 */

public interface ServiceListener {
    void onGetUser();
    void onCreateUser();

    void onGetClothe();
    void onCreateClothe();
    void onDeleteClothe();
    void onManageClothes();

    void onGetClothes();
    void onCreateClothes();
    void onDeleteClothes();
    void onManageClothe();

    void onGetSimilarity();

    void onCreatePost();
}
