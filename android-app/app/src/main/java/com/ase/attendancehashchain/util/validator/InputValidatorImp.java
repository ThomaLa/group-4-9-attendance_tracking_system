package com.ase.attendancehashchain.util.validator;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by MarcoSalazar on 28/01/18.
 *
 */

public class InputValidatorImp implements InputValidator {

    private Activity activity;

    public InputValidatorImp(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean validateInputEmailAndPassword(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this.activity, "Enter email address!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this.activity, "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.contains("@")) {
            Toast.makeText(this.activity, "Please enter a valid email address", Toast.LENGTH_LONG).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this.activity, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
