package com.lmesa.dressy.network;

import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.ListClothes;
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

    String baseUrl = "";

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
            @Body User user
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
            @Body User user
    );



    // SIMILARITY

    // GET SIMILIRARITY
    @POST("v1/getClothes")
    Call<Clothe> getSimilarityClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe);









}
