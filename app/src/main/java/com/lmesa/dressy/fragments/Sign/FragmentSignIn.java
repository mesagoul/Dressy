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
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.activities.MainActivity;
import com.lmesa.dressy.helpers.FormValidator;
import com.lmesa.dressy.models.User;
import com.lmesa.dressy.network.ApiDressy;

/**
 * Created by Lucas on 03/04/2017.
 */

public class FragmentSignIn extends Fragment {
    private Button btn_connexion;
    private TextView btn_forgot_password;
    private ApiDressy apiDressy;
    private EditText password;
    private EditText email;

    private FormValidator formValidator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, container, false);
        btn_connexion = (Button) v.findViewById(R.id.sign_connexion);
        btn_forgot_password = (TextView) v.findViewById(R.id.sign_forgot_password);
        email = (EditText) v.findViewById(R.id.sign_mail);
        password = (EditText) v.findViewById(R.id.sign_password);

        formValidator = new FormValidator();

        apiDressy = new ApiDressy(getActivity());
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
                    apiDressy.connectUser(user);
                    Intent toMainActivity = new Intent(getActivity(), MainActivity.class);
                    startActivity(toMainActivity);
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
        // Check for a valid email address.
        if ( email.getText().toString().trim().length() == 0) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!formValidator.isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        } else if (!formValidator.isEmailValid(password.getText().toString())) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
