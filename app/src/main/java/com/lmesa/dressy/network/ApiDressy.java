package com.lmesa.dressy.network;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.ActivityWardRobeClotheList;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.ListClothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.models.User;

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
    private Service service;
    private ServiceListener listener;

    public ApiDressy(Activity activity){
        this.activity = activity;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dressyapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
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
                    Toast.makeText(activity.getApplicationContext(), response.body().getApi_key(), Toast.LENGTH_SHORT).show();
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
     * @param user
     */
    public void getClothe(User user){
        Call<Clothes> request = service.getClothe(user);
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onGetClothe(true);
                }else{
                    listener.onGetClothe(false);
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                listener.onGetClothe(false);
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
                    listener.onCreateClothe(true);
                }else{
                    listener.onCreateClothe(false);
                }
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                listener.onCreateClothe(false);
            }
        });
    }
    /**
     * Add clothe for user
     * @param clothe
     */
    public void deleteClothe(Clothe clothe){
        Call<Clothe> request = service.deleteClothe(getAccesToken(),clothe);
        request.enqueue(new Callback<Clothe>() {
            @Override
            public void onResponse(Call<Clothe> call, Response<Clothe> response) {
                if(response.isSuccessful()){
                    listener.onDeleteClothe(true);
                }else{
                    listener.onDeleteClothe(false);
                }
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
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
     * @param user
     */
    public void getClothes(User user){
        Call<ListClothes> request = service.getClothes(user);
        request.enqueue(new Callback<ListClothes>() {
            @Override
            public void onResponse(Call<ListClothes> call, Response<ListClothes> response) {
                if(response.isSuccessful()){
                    listener.onGetClothes(true);
                }else{
                    listener.onGetClothes(false);
                }
            }

            @Override
            public void onFailure(Call<ListClothes> call, Throwable t) {
                listener.onGetClothes(false);
            }
        });
    }
    /**
     * Add clothes for user
     * @param clothes
     */
    public void addClothes(Clothes clothes){
        Call<Clothes> request = service.addClothes(getAccesToken(),clothes);
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onCreateClothes(true);
                }else{
                    listener.onCreateClothes(false);
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                listener.onCreateClothes(false);
            }
        });
    }


    /**
     * Add clothes for user
     * @param clothes
     */
    public void deleteClothes(Clothes clothes){
        Call<Clothes> request = service.deleteClothes(getAccesToken(),clothes);
        request.enqueue(new Callback<Clothes>() {
            @Override
            public void onResponse(Call<Clothes> call, Response<Clothes> response) {
                if(response.isSuccessful()){
                    listener.onDeleteClothes(true);
                }else{
                    listener.onDeleteClothes(false);
                }
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
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
}
