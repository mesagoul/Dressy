package com.lmesa.dressy.interfaces;

/**
 * Created by Lucas on 15/04/2017.
 */

public interface ServiceListener {
    void onGetUser(boolean isSucces);
    void onCreateUser(boolean isSucces);

    void onGetClothe(boolean isSucces);
    void onCreateClothe(boolean isSucces);
    void onDeleteClothe(boolean isSucces);
    void onManageClothes(boolean isSucces);

    void onGetClothes(boolean isSucces);
    void onCreateClothes(boolean isSucces);
    void onDeleteClothes(boolean isSucces);
    void onManageClothe(boolean isSucces);

    void onGetSimilarity(boolean isSucces);

    void onCreatePost(boolean isSucces);
}
