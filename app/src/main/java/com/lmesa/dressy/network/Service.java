package com.lmesa.dressy.network;

import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.ListClothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mac13 on 10/04/2017.
 */

public interface Service {


    // USERS
    @POST("v1/register")
    Call<User> createUserAccount(
            @Body User user
    );

    @POST("v1/login")
    Call<User> connectUserAccount(
            @Body User user
    );


    // ---- CLOTHE ----

    // GET CLOTHE
    @GET("v1/getClothe")
    Call<Clothes> getClothe(
            @Body User user
    );

    // Add CLOTHE
    @POST("v1/addClothe")
    Call<Clothe> addClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );

    // delete CLOTHE
    @POST("v1/deleteClothe")
    Call<Clothe> deleteClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );

    // manage CLOTHE
    @POST("v1/manageClothe")
    Call<Clothe> manageClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );


    // ---- CLOTHES ----

    // GET CLOTHES
    @GET("v1/getClothes")
    Call<ListClothes> getClothes(
            @Body User user
    );

    // Add CLOTHES
    @POST("v1/addClothes")
    Call<Clothes> addClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );

    // delete CLOTHES
    @POST("v1/deleteClothes")
    Call<Clothes> deleteClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );

    // manage CLOTHES
    @POST("v1/manageClothes")
    Call<Clothes> manageClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );



    // SIMILARITY

    // GET SIMILIRARITY
    @POST("v1/getClothes")
    Call<Clothe> getSimilarityClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe);

    // POST
    // ADD POST
    @POST("v1/addPost")
    Call<Post> createPost(
            @Header("x-access-token") String token,
            @Body Post post);
}
