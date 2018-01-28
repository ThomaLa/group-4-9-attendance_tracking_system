package com.ase.attendancehashchain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ase.attendancehashchain.R;
import com.ase.attendancehashchain.service.authentication.Login;
import com.ase.attendancehashchain.service.authentication.imp.LoginImp;
import com.ase.attendancehashchain.util.InputValidator;
import com.ase.attendancehashchain.util.InputValidatorImp;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin;
    private FirebaseAuth auth;
    private Login login;
    private InputValidator inputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Init login service
        login = new LoginImp(auth, this);

        // Init inputValidator util
        inputValidator = new InputValidatorImp(this);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    public void doLogin() {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (inputValidator.validateInputEmailAndPassword(email, password)) {
            setProgressBarVisible(View.VISIBLE);
            login.signInWithEmailAndPassword(email, password);
        }
    }

    public void setProgressBarVisible(int status) {
        this.progressBar.setVisibility(status);
    }
}