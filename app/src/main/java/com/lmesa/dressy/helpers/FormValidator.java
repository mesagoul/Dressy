package com.lmesa.dressy.helpers;

import android.view.View;

import com.lmesa.dressy.R;

/**
 * Created by mac13 on 10/04/2017.
 */

public class FormValidator {

    public boolean isEmailValid(String email) {
        return email.contains("@");
    }
}
