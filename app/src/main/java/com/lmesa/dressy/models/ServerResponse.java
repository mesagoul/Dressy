package com.lmesa.dressy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lucas on 23/05/2017.
 */

public class ServerResponse {
    // variable name should be same as in the json response from php    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;
    @SerializedName("path")
    String path;

    public String getMessage() {
        return message;
    }

    public String getUrlImage() {
        return path;
    }

    boolean getSuccess() {
        return success;
    }
}
