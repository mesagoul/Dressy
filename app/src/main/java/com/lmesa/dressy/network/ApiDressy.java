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

                }else{
                    Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
                }
                listener.onCreateUser();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //listener.onGetUser();
                }
                listener.onGetUser();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
                listener.onGetUser();
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
                    //TODO
                    // response.body().getListClothe();
                }
                listener.onGetClothe();
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                    //listener.onCreateClothe();
                }
                listener.onCreateClothe();
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onDeleteClothe();
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onManageClothe();
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onGetClothes();
            }

            @Override
            public void onFailure(Call<ListClothes> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onCreateClothes();
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onDeleteClothes();
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                }
                listener.onManageClothes();
            }

            @Override
            public void onFailure(Call<Clothes> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
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
                    //TODO
                    //listener.onGetSimilarity();
                }
                listener.onGetSimilarity();
            }

            @Override
            public void onFailure(Call<Clothe> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
                listener.onGetSimilarity();
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
                    //TODO
                }
                listener.onCreatePost();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
