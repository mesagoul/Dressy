package com.lmesa.dressy.network;

import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.ListClothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.models.Posts;
import com.lmesa.dressy.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by mac13 on 10/04/2017.
 */

public interface Service {


    // USERS
    @POST("register")
    Call<User> createUserAccount(
            @Body User user
    );

    @POST("login")
    Call<User> connectUserAccount(
            @Body User user
    );


    // ---- CLOTHE ----

    // GET CLOTHE
    @GET("getClothe")
    Call<Clothes> getClothe(
            @Header("x-access-token") String token
    );

    // Add CLOTHE
    @POST("addClothe")
    Call<Clothe> addClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );

    // Add IMAGE CLOTHE
    @Multipart
    @POST("addImageClothe")
    Call<Clothe> addImageClothe(
            @Header("x-access-token") String token,
            @Part MultipartBody.Part file,
            @Part("file") RequestBody name
    );

    // delete CLOTHE
    @POST("deleteClothe")
    Call<Void> deleteClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );

    // manage CLOTHE
    @POST("updateClothe")
    Call<Clothe> manageClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe
    );


    // ---- CLOTHES ----

    // GET CLOTHES
    @GET("getClothes")
    Call<ListClothes> getClothes(
            @Header("x-access-token") String token
    );

    // Add CLOTHES
    @POST("addClothes")
    Call<Clothes> addClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );

    // delete CLOTHES
    @POST("deleteClothes")
    Call<Void> deleteClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );

    // manage CLOTHES
    @POST("updateClothes")
    Call<Clothes> manageClothes(
            @Header("x-access-token") String token,
            @Body Clothes clothes
    );



    // SIMILARITY

    // GET SIMILIRARITY
    @POST("getSimilarity")
    Call<Clothe> getSimilarityClothe(
            @Header("x-access-token") String token,
            @Body Clothe clothe);


    // GET getClotheProperties
    @GET("getClotheProperties")
    Call<ClotheProperties> getClotheProperties(
            @Header("x-access-token") String token
    );

    // POST
    // ADD POST
    @POST("addPost")
    Call<Post> createPost(
            @Header("x-access-token") String token,
            @Body Post post);

    // // POST
    // GET TOP POST
    @GET("getTopPost")
    Call<Posts> getTopPost(
            @Header("x-access-token") String token);

    // GET LAST POST
    @GET("getLastPost")
    Call<Posts> getLastPost(
            @Header("x-access-token") String token);
}


