package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    private EditText ediTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private TextView btnSignIn;
    private FarmRepository repoInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btnSignUp = findViewById(R.id.signUp);
        editTextPassword = findViewById(R.id.password);
        ediTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.email);
        btnSignIn = findViewById(R.id.btnSignIn);

        repoInstance = FarmRepository.getFarmRepositoryInstance(this);


        btnSignUp.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String username = ediTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            addToDatabase(email, password,username);
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }



    private void addToDatabase(String email, String password, String username) {
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(username.isEmpty()){
            ediTextUsername.setError("Username is required");
            ediTextUsername.requestFocus();
            return;
        }


        if(password.length() < 6){
            editTextPassword.setError("password can not be less than 6");
            editTextPassword.requestFocus();
            return;
        }
        //This will trigger what adds the user to the database
        ProgressBar prog = findViewById(R.id.progressBar);
        repoInstance.signUp(email,password,this,prog);
        
    }
}