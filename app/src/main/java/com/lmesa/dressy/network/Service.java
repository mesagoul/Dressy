package com.lmesa.dressy.network;

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
    Call<String> createUserAccount(
            @Body User user
    );

    @POST("v1/log_in")
    Call<String> connectUserAccount(
            @Body User user
    );


}
