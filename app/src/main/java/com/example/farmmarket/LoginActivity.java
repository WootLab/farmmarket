package com.example.farmmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextName;
    private TextInputEditText editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextName = findViewById(R.id.logemail);
        editTextPassword = findViewById(R.id.logPass);
        Button logbtn = findViewById(R.id.logbutton);
        TextView ntmb = findViewById(R.id.txtnamb);
        mAuth =FirebaseAuth.getInstance();

        logbtn.setOnClickListener(v -> login());
        ntmb.setOnClickListener(v-> moveToSignUp());
    }

    private void moveToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart","Am chilling in Onstart");
        new FirebaseAuth.AuthStateListener() {
            final FirebaseUser fireBaseUser = mAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (fireBaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are already logged in", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    private void login() {

            String email = Objects.requireNonNull(editTextName.getText()).toString().trim();
            String password = Objects.requireNonNull(editTextPassword.getText()).toString().trim();
            ProgressBar bar = findViewById(R.id.progressBar2);
            FarmRepository.getFarmRepositoryInstance(getApplicationContext()).login(email, password,this,bar,editTextName,editTextPassword);

    }
}