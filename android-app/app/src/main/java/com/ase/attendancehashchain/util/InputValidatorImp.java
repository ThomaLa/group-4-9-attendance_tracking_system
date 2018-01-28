package com.ase.attendancehashchain.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.ase.attendancehashchain.ui.SignUpActivity;

/**
 * Created by MarcoSalazar on 28/01/18.
 *
 */

public class InputValidatorImp implements InputValidator {

    private SignUpActivity signUpActivity;

    public InputValidatorImp(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    @Override
    public boolean validateInputEmailAndPassword(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this.signUpActivity, "Enter email address!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this.signUpActivity, "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.contains("@")) {
            Toast.makeText(this.signUpActivity, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this.signUpActivity, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
