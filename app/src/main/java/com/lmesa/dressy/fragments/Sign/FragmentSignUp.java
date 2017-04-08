package com.lmesa.dressy.fragments.Sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lmesa.dressy.R;

/**
 * Created by Lucas on 08/04/2017.
 */

public class FragmentSignUp extends Fragment {
    private Button btn_inscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up,container,false);
        btn_inscription = (Button) v.findViewById(R.id.btn_sign_up);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
                Log.d("DEBUG","CLICK INSCRIPTION");
            }
        });
    }
}
