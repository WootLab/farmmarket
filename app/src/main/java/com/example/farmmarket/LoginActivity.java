package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextName;
    private TextInputEditText editTextPassword;
    private Button logbtn;
    private TextView ntmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextName = findViewById(R.id.logemail);
        editTextPassword = findViewById(R.id.logPass);
        logbtn = findViewById(R.id.logbutton);
        ntmb = findViewById(R.id.txtnamb);
        logbtn.setOnClickListener(v -> login());
        ntmb.setOnClickListener(v-> moveToSignUp());
    }

    private void moveToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);
    }


    private void login() {
        if(editTextPassword != null && editTextName != null){
            String email = Objects.requireNonNull(editTextName.getText()).toString().trim();
            String password = Objects.requireNonNull(editTextPassword.getText()).toString().trim();

            if(email.isEmpty()){
                editTextName.setError("Email cnt be empty");
                editTextName.requestFocus();
            }

            if(password.isEmpty()){
                editTextPassword.setError("Password Cant be empty");
                editTextPassword.requestFocus();
            }

            ProgressBar bar = findViewById(R.id.progressBar2);
            FarmRepository.getFarmRepositoryInstance(getApplicationContext()).login(email, password,this,bar);

        }else{
            Toast.makeText(this,"Please either password is missing",Toast.LENGTH_LONG).show();
            editTextName.requestFocus();
        }

    }
}