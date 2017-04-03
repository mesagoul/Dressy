package com.lmesa.dressy;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Lucas on 03/04/2017.
 */

public class SignInUpActivity extends FragmentActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_in_up_activity);
        toolbar = (Toolbar) findViewById(R.id.sign_toolbar);


    }
}
