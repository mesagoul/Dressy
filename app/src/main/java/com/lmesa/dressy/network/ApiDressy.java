package com.lmesa.dressy.network;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import com.lmesa.dressy.R;
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
    private String token;
    private Retrofit retrofit;
    private Service service;

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
        token = settings.getString("token", new String());
        return token;
    }


    /**
     * Create user account
     * @param user
     */
    public void createUser(User user){
        Call<String> request = service.createUserAccount(user);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    SharedPreferences settings = activity.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token", response.body());
                    editor.commit();
                    Toast.makeText(activity.getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Connect user account
     * @param user
     */
    public void connectUser(User user){
        Call<String> request = service.connectUserAccount(user);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(activity.getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.error_sign_up), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
