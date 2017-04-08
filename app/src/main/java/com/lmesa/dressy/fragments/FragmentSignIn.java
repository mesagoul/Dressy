package com.lmesa.dressy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.MainActivity;

/**
 * Created by Lucas on 03/04/2017.
 */

public class FragmentSignIn extends Fragment {
    private Button btn_connexion;
    private TextView btn_forgot_password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, container, false);
        btn_connexion = (Button) v.findViewById(R.id.sign_connexion);
        btn_forgot_password = (TextView) v.findViewById(R.id.sign_forgot_password);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
                Log.d("DEBUG","Button CONNEXION CLICKED");
                Intent toMainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(toMainActivity);
            }
        });

        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
                Log.d("DEBUG","Button FORGOT CLICKED");
            }
        });
    }
}
