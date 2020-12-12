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
    private EditText editTextPhone;
    private EditText editTextFullName;
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
        editTextPhone = findViewById(R.id.phoneEd);
        editTextFullName = findViewById(R.id.fullname);

        btnSignIn = findViewById(R.id.btnSignIn);

        repoInstance = FarmRepository.getFarmRepositoryInstance(this);


        btnSignUp.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String username = ediTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();
            String fullname = editTextFullName.getText().toString().trim();
            addToDatabase(email, password,username,phone,fullname);
        });

        btnSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this,LoginActivity.class);
            startActivity(intent);
        });
    }



    private void addToDatabase(String email, String password, String username,String phone,String fullname) {
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(username.isEmpty()){
            ediTextUsername.setError("Username is requi" +
                    "red");
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
        repoInstance.signUp(email,password,this,prog,phone,username,fullname);
        
    }
}