package com.lmesa.dressy.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.lmesa.dressy.activities.ActivityManageClothe;
import com.lmesa.dressy.activities.ActivityManageClothes;
import com.lmesa.dressy.activities.MainActivity;

/**
 * Created by Lucas on 27/05/2017.
 */

public class InternalStorage extends AppCompatActivity {
    private Activity activity;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;

    public InternalStorage(Activity activity) {
        this.activity = activity;
    }

    public void askForPermission(){
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this.activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this.activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }else{
            if(this.activity instanceof ActivityManageClothe){
                ((ActivityManageClothe)this.activity).launchCamera();
            }
            if(this.activity instanceof ActivityManageClothes){
                ((ActivityManageClothes)this.activity).launchCamera();
            }
        }
    }


    public final int getExternalStorageCode(){
        return this.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
    }


}
