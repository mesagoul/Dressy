package com.lmesa.dressy.fragments.Sign;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.MainActivity;
import com.lmesa.dressy.helpers.FormValidator;
import com.lmesa.dressy.helpers.ResponseHttp;
import com.lmesa.dressy.interfaces.ServiceListener;
import com.lmesa.dressy.models.Clothe.Clothe;
import com.lmesa.dressy.models.Clothe.ClotheProperties;
import com.lmesa.dressy.models.Clothes;
import com.lmesa.dressy.models.Post;
import com.lmesa.dressy.models.User;
import com.lmesa.dressy.network.ApiDressy;

import java.util.ArrayList;

/**
 * Created by Lucas on 03/04/2017.
 */

public class FragmentSignIn extends Fragment implements ServiceListener{
    private Button btn_connexion;
    private TextView btn_forgot_password;
    private ApiDressy apiDressy;
    private EditText password;
    private EditText email;

    private ScrollView content;
    private ProgressBar progressBar;


    private FormValidator formValidator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, container, false);
        btn_connexion = (Button) v.findViewById(R.id.sign_connexion);
        btn_forgot_password = (TextView) v.findViewById(R.id.sign_forgot_password);
        email = (EditText) v.findViewById(R.id.sign_mail);
        password = (EditText) v.findViewById(R.id.sign_password);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        content = (ScrollView) v.findViewById(R.id.view_content);

        formValidator = new FormValidator();

        apiDressy = new ApiDressy(getActivity());
        apiDressy.setListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidUser()){
                    User user = new User(email.getText().toString(),password.getText().toString());

                    content.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                    apiDressy.connectUser(user);

                }
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

    private boolean isValidUser() {
        View focusView = null;
        boolean cancel = false;
        // Check for if mail is empty
        if ( email.getText().toString().trim().length() == 0) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
            // Check for a valid email address.
        } else if (!formValidator.isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }else if ( password.getText().toString().trim().length() == 0) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
            // Check for a valid email address.
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onGetUser(boolean isSucces) {
        content.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if(isSucces){
            Intent toMainActivity = new Intent(getActivity(), MainActivity.class);
            startActivity(toMainActivity);
        }else{
            new ResponseHttp(getContext()).onErrorGetUser();
        }
    }

    @Override
    public void onCreateUser(boolean isSucces) {

    }

    @Override
    public void onGetClothe(boolean isSucces, ArrayList<Clothe> listClothe) {

    }

    @Override
    public void onCreateClothe(boolean isSucces, int cloth_id) {

    }

    @Override
    public void onDeleteClothe(boolean isSucces) {

    }

    @Override
    public void onManageClothes(boolean isSucces) {

    }

    @Override
    public void onGetClothes(boolean isSucces, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSucces, int id) {

    }

    @Override
    public void onDeleteClothes(boolean isSucces) {

    }

    @Override
    public void onManageClothe(boolean isSucces) {

    }

    @Override
    public void onGetSimilarity(boolean isSucces, Clothes listClothe) {

    }

    @Override
    public void onCreatePost(boolean isSucces) {

    }

    @Override
    public void onGetClotheProperties(boolean isSuccess, ClotheProperties clotheProperties) {

    }

    @Override
    public void onGetTopPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onGetLastPosts(boolean isSuccess, ArrayList<Post> listPost) {

    }

    @Override
    public void onAddImage(boolean isSuccess, String urlImage) {

    }
}
