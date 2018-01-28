package com.ase.attendancehashchain.service.authentication.signUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.ase.attendancehashchain.ui.MainActivity;
import com.ase.attendancehashchain.ui.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by MarcoSalazar on 28/01/18.
 *
 */

public class SignUpImp implements SignUp {
    private FirebaseAuth auth;
    private SignUpActivity signUpActivity;

    public SignUpImp(FirebaseAuth auth, SignUpActivity signUpActivity) {
        this.auth = auth;
        this.signUpActivity = signUpActivity;
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(signUpActivity, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                signUpActivity.setProgressBarVisible(View.GONE);

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.

                if (!task.isSuccessful()) {
                    Toast.makeText(signUpActivity, "Authentication failed." + task.getException(),
                    Toast.LENGTH_SHORT).show();
                } else {
                    signUpActivity.startActivity(new Intent(signUpActivity, MainActivity.class));
                    signUpActivity.finish();
                }
            }
        });
    }
}
