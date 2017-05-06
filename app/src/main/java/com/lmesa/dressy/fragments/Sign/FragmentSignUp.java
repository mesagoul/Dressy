package com.lmesa.dressy.fragments.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by Lucas on 08/04/2017.
 */

public class FragmentSignUp extends Fragment implements ServiceListener {
    private Button btn_inscription;

    private TextView first_name;
    private TextView last_name;
    private TextView mail;
    private TextView pseudo;
    private TextView password;
    private TextView country;

    private ScrollView content;

    private ApiDressy apiDressy;
    private FormValidator formValidator;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up,container,false);
        btn_inscription = (Button) v.findViewById(R.id.btn_sign_up);
        first_name = (TextView) v.findViewById(R.id.sign_up_first_name);
        last_name = (TextView) v.findViewById(R.id.sign_up_last_name);
        mail = (TextView) v.findViewById(R.id.sign_up_mail);
        pseudo = (TextView) v.findViewById(R.id.sign_up_pseudo);
        password = (TextView) v.findViewById(R.id.sign_up_password);
        country = (TextView) v.findViewById(R.id.sign_up_country);

        content = (ScrollView) v.findViewById(R.id.view_content);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);


        formValidator = new FormValidator();

        mail.setError(null);

        apiDressy = new ApiDressy(getActivity());
        apiDressy.setListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidUser()){
                    progressBar.setVisibility(View.VISIBLE);
                    content.setVisibility(View.GONE);
                    User user = new User(first_name.getText().toString(),last_name.getText().toString(),mail.getText().toString(),pseudo.getText().toString(),country.getText().toString(),password.getText().toString());
                    apiDressy.createUser(user);
                }
            }
        });
    }

    private boolean isValidUser() {
        View focusView = null;
        boolean cancel = false;
        // Check for a valid email address.
        if ( mail.getText().toString().trim().length() == 0) {
            mail.setError(getString(R.string.error_field_required));
            focusView = mail;
            cancel = true;
        } else if (!formValidator.isEmailValid(mail.getText().toString())) {
            mail.setError(getString(R.string.error_invalid_email));
            focusView = mail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onGetUser(boolean isSuccess) {

    }

    @Override
    public void onCreateUser(boolean isSuccess) {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        if(isSuccess){
            Intent toMainActivity = new Intent(getActivity(), MainActivity.class);
            startActivity(toMainActivity);
        }else{
            new ResponseHttp(getContext()).onErrorCreateUser();
        }

    }

    @Override
    public void onGetClothe(boolean isSuccess, ArrayList<Clothe> listClothe) {

    }

    @Override
    public void onCreateClothe(boolean isSuccess, int cloth_id) {

    }

    @Override
    public void onDeleteClothe(boolean isSuccess) {

    }

    @Override
    public void onManageClothes(boolean isSuccess) {

    }

    @Override
    public void onGetClothes(boolean isSuccess, ArrayList<Clothes> clothes) {

    }

    @Override
    public void onCreateClothes(boolean isSuccess, int id) {

    }

    @Override
    public void onDeleteClothes(boolean isSuccess) {

    }

    @Override
    public void onManageClothe(boolean isSuccess) {

    }

    @Override
    public void onGetSimilarity(boolean isSuccess) {

    }

    @Override
    public void onCreatePost(boolean isSuccess) {

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
}
