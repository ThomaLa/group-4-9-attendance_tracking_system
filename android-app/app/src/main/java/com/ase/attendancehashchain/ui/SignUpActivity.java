package com.ase.attendancehashchain.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ase.attendancehashchain.R;

import com.ase.attendancehashchain.service.authentication.SignUp;
import com.ase.attendancehashchain.service.authentication.imp.SignUpImp;
import com.ase.attendancehashchain.util.InputValidator;
import com.ase.attendancehashchain.util.InputValidatorImp;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A sign up screen that offers registration via email/password.
 */
public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private SignUp signUp;
    private InputValidator inputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Init signUp service
        signUp = new SignUpImp(auth, this);

        // Init inputValidator util
        inputValidator = new InputValidatorImp(this);

        btnSignIn = findViewById(R.id.sign_in_button);
        btnSignUp = findViewById(R.id.sign_up_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUp();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void doSignUp() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (inputValidator.validateInputEmailAndPassword(email, password)) {
            setProgressBarVisible(View.VISIBLE);
            signUp.createUserWithEmailAndPassword(email, password);
        }
    }

    public void setProgressBarVisible(int status) {
        this.progressBar.setVisibility(status);
    }
}

