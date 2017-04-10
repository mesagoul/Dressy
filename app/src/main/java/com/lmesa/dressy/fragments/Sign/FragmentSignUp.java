package com.lmesa.dressy.fragments.Sign;

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

        apiDressy = new ApiDressy(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(first_name.getText().toString(),last_name.getText().toString(),mail.getText().toString(),pseudo.getText().toString(),country.getText().toString(),password.getText().toString());
                apiDressy.createUser(user);
            }
        });
    }
}
