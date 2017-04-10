package com.lmesa.dressy.fragments.Sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lmesa.dressy.R;
import com.lmesa.dressy.helpers.FormValidator;
import com.lmesa.dressy.models.User;
import com.lmesa.dressy.network.ApiDressy;

/**
 * Created by Lucas on 08/04/2017.
 */

public class FragmentSignUp extends Fragment {
    private Button btn_inscription;

    private TextView first_name;
    private TextView last_name;
    private TextView mail;
    private TextView pseudo;
    private TextView password;
    private TextView country;

    private ApiDressy apiDressy;
    private FormValidator formValidator;

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

        formValidator = new FormValidator();

        mail.setError(null);

        apiDressy = new ApiDressy(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidUser()){
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
}
