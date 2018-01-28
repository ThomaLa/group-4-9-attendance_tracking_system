package com.ase.attendancehashchain.service.authentication.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.ase.attendancehashchain.R;
import com.ase.attendancehashchain.ui.LoginActivity;
import com.ase.attendancehashchain.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by MarcoSalazar on 28/01/18.
 *
 */

public class LoginImp implements Login {

    private FirebaseAuth auth;
    private LoginActivity loginActivity;

    public LoginImp(FirebaseAuth auth, LoginActivity loginActivity) {
        this.auth = auth;
        this.loginActivity = loginActivity;
    }

    @Override
    public void signInWithEmailAndPassword(final String email, final String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(loginActivity, "signInWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                loginActivity.setProgressBarVisible(View.GONE);

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.

                if (!task.isSuccessful()) {
                    // there was an error
                    if (password.length() < 6) {
                        Toast.makeText(loginActivity, loginActivity.getString(R.string.minimum_password), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(loginActivity, loginActivity.getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent mainActivity = new Intent(loginActivity, MainActivity.class);
                    mainActivity.putExtra("username", email);
                    loginActivity.startActivity(mainActivity);
                    loginActivity.finish();
                }
            }
        });
    }
}
