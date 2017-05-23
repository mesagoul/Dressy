package com.lmesa.dressy.network;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.lmesa.dressy.helpers.NullOnEmptyConverterFactory;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.ListClothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.models.Posts;
import com.lmesa.dressy.models.ServerResponse;
import com.lmesa.dressy.models.User;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac13 on 10/04/2017.
 */

public class ApiDressy{
    private Activity activity;
    private Retrofit retrofit;
    private Retrofit retrofitImage;
    private Service service;
    private Service serviceImage;
    private ServiceListener listener;

    public ApiDressy(Activity activity){
        this.activity = activity;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://51.254.101.16/dressyApi/v1/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .build();
        retrofitImage = new Retrofit.Builder()
                .baseUrl("http://51.254.101.16/uploads/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .build();
        service = retrofit.create(Service.class);
        serviceImage = retrofitImage.create(Service.class);
    }

    public String getAccesToken(){
        SharedPreferences settings = this.activity.getSharedPreferences("token", 0);
        return settings.getString("token", new String());
    }



    // --------------------------------------------------------------------------------------------------
    // ------------------------------------- USER -------------------------------------------------------
    // --------------------------------------------------------------------------------------------------

    /**
     * Create user account
     * @param user
     */
    public void createUser(User user){
        Call<User> request = service.createUserAccount(user);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SharedPreferences settings = activity.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token", response.body().getApi_key());
                    editor.commit();
                    listener.onCreateUser(true);
                }else{
                    listener.onCreateUser(false);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onCreateUser(false);
            }
        });
    }


    /**
     * Connect user account
     * @param user
     */
    public void connectUser(User user){
        Call<User> request = service.connectUserAccount(user);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SharedPreferences settings = activity.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token", response.body().getApi_key());
                    editor.commit();
                    listener.onGetUser(true);
                }else{
                    listener.onGetUser(false);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onGetUser(false);
            }
        });
    }


    // --------------------------------------------------------------------------------------------------
    // ------------------------------------- CLOHTE -----------------------------------------------------
    // --------------------------------------------------------------------------------------------------


    /**
     * Get clothe from user
     */
    public void getClothe(){
        Call<Clothes> request = service.getClothe(getAccesToken());
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onGetClothe(true, response.body().getListClothe());

                }else{
                    listener.onGetClothe(false, new ArrayList<Clothe>());
                }
            }


            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                listener.onGetClothe(false, new ArrayList<Clothe>());
            }
        });
    }


    /**
     * Add clothe for user
     * @param clothe
     */
    public void addClothe(Clothe clothe){

        Call<Clothe> request = service.addClothe(getAccesToken(),clothe);
        request.enqueue(new Callback<Clothe>() {
            @Override
            public void onResponse(Call<Clothe> call, Response<Clothe> response) {
                if(response.isSuccessful()){
                    listener.onCreateClothe(true, response.body().getCloth_id());
                }else{
                    listener.onCreateClothe(false, 0);
                }
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                listener.onCreateClothe(false, 0);
            }
        });
    }

    public void addImageClothe(File imageFile, String name){
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), imageFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), imageFile.getName());

        Call<ServerResponse> request = serviceImage.addImageClothe(getAccesToken(),fileToUpload, filename );
        request.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){
                    ServerResponse serverResponse = response.body();
                    listener.onAddImage(true, "http://51.254.101.16/uploads/"+serverResponse.getUrlImage());
                }else{
                    listener.onAddImage(false, "" );
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                listener.onAddImage(false, "");
            }
        });
    }




    /**
     * Add clothe for user
     * @param clothe
     */
    public void deleteClothe(Clothe clothe){
        Call<Void> request = service.deleteClothe(getAccesToken(),clothe);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    listener.onDeleteClothe(true);
                }else{
                    listener.onDeleteClothe(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteClothe(false);
            }
        });
    }
    /**
     * Add clothe for user
     * @param clothe
     */
    public void manageClothe(Clothe clothe){
        Call<Clothe> request = service.manageClothe(getAccesToken(),clothe);
        request.enqueue(new Callback<Clothe>() {
            @Override
            public void onResponse(Call<Clothe> call, Response<Clothe> response) {
                if(response.isSuccessful()){
                    listener.onManageClothe(true);
                }else{
                    listener.onManageClothe(false);
                }
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                listener.onManageClothe(false);
            }
        });
    }



    // --------------------------------------------------------------------------------------------------
    // ------------------------------------- CLOHTES ----------------------------------------------------
    // --------------------------------------------------------------------------------------------------


    /**
     * Get clothes from user
     */
    public void getClothes(){
        Call<ListClothes> request = service.getClothes(getAccesToken());
        request.enqueue(new Callback<ListClothes>() {
            @Override
            public void onResponse(Call<ListClothes> call, Response<ListClothes> response) {
                if(response.isSuccessful()){
                    listener.onGetClothes(true, response.body().getClothes());
                }else{
                    listener.onGetClothes(false,new ArrayList<Clothes>());
                }
            }

            @Override
            public void onFailure(Call<ListClothes> call, Throwable t) {
                listener.onGetClothes(false,new ArrayList<Clothes>());
            }
        });
    }
    /**
     * Add clothes for user
     * @param clothes
     */
    public void addClothes(final Clothes clothes){
        Call<Clothes> request = service.addClothes(getAccesToken(),clothes);
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onCreateClothes(true, response.body().getId());
                }else{
                    listener.onCreateClothes(false, 0);
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                listener.onCreateClothes(false, 0);
            }
        });
    }


    /**
     * Add clothes for user
     * @param clothes
     */
    public void deleteClothes(Clothes clothes){
        Call<Void> request = service.deleteClothes(getAccesToken(),clothes);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    listener.onDeleteClothes(true);
                }else{
                    listener.onDeleteClothes(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteClothes(false);
            }
        });
    }


    /**
     * Add clothes for user
     * @param clothes
     */
    public void manageClothes(Clothes clothes){
        Call<Clothes> request = service.manageClothes(getAccesToken(),clothes);
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onManageClothes(true);
                }else{
                    listener.onManageClothes(false);
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                listener.onManageClothes(false);
            }
        });
    }




    /**
     * Get similarity between clothe user and this armory clothe
     * @param @clothe
     */
    public void getSimilarity(Clothe clothe){
        Call<Clothe> request = service.getSimilarityClothe(getAccesToken(),clothe);
        request.enqueue(new Callback<Clothe>() {
            @Override
            public void onResponse(Call<Clothe> call, Response<Clothe> response) {
                if(response.isSuccessful()){
                    listener.onGetSimilarity(true);
                }else{
                    listener.onGetSimilarity(false);
                }
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                listener.onGetSimilarity(false);
            }
        });
    }

    public void setListener(ServiceListener listener) {
        this.listener = listener;
    }

    public void createPost(Post post) {
        Call<Post> request = service.createPost(getAccesToken(),post);
        request.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    listener.onCreatePost(true);
                }else{
                    listener.onCreatePost(false);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                listener.onCreatePost(false);
            }
        });
    }



    /**
     * Get list of Top Posts
     * @param @clothe
     */
    public void getTopPosts(){
        Call<Posts> request = service.getTopPost(getAccesToken());
        request.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(response.isSuccessful()){
                    listener.onGetTopPosts(true, response.body().getPosts());
                }else{
                    listener.onGetTopPosts(false,  new ArrayList<Post>());
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                listener.onGetTopPosts(false,  new  ArrayList<Post>());
            }
        });
    }

    /**
     * Get list of Top Posts
     * @param @clothe
     */
    public void getLastPosts(){
        Call<Posts> request = service.getLastPost(getAccesToken());
        request.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(response.isSuccessful()){
                    listener.onGetLastPosts(true, response.body().getPosts());
                }else{
                    listener.onGetLastPosts(false,  new ArrayList<Post>());
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                listener.onGetLastPosts(false,  new  ArrayList<Post>());
            }
        });
    }

    /**
     * Get list of Properties of a Clothe
     * @param @clothe
     */
    public void getClotheProperties(){
        Call<ClotheProperties> request = service.getClotheProperties(getAccesToken());
        request.enqueue(new Callback<ClotheProperties>() {
            @Override
            public void onResponse(Call<ClotheProperties> call, Response<ClotheProperties> response) {
                if(response.isSuccessful()){
                    listener.onGetClotheProperties(true, response.body());
                }else{
                    listener.onGetClotheProperties(false, new ClotheProperties());
                }
            }

            @Override
            public void onFailure(Call<ClotheProperties> call, Throwable t) {
                listener.onGetClotheProperties(false,  new ClotheProperties());
            }
        });
    }
}
