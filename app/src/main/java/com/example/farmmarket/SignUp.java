package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    private EditText ediTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextFullName;

    private TextView btnSignIn;
    private Button btnSignUp;

    private TextInputLayout textInputLayoutUsername,
            textInputLayoutPassword,
            textInputLayoutEmail,
            textInputLayoutPhone,
            textInputLayoutFullName;
    private FarmRepository repoInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        defineViews();

        setAnimation(textInputLayoutFullName,1000);
        setAnimation(textInputLayoutEmail,2000);
        setAnimation(textInputLayoutUsername,1500);
        setAnimation(textInputLayoutPhone,3000);
        setAnimation(textInputLayoutPassword,2500);

        setAnimation(editTextEmail);
        setAnimation(ediTextUsername);
        setAnimation(editTextPassword);
        setAnimation(editTextPhone);
        setAnimation(editTextFullName);
        setAnimation(btnSignUp);

        ImageView imageView = findViewById(R.id.imageView3);
        YoYo.with(Techniques.ZoomInLeft).duration(3000).repeat(1).playOn(imageView);
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
            finish();
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

        AccSharedPref.setStoredEmail(this,email);
        //This will trigger what adds the user to the database
        ProgressBar prog = findViewById(R.id.progressBar);
        repoInstance.signUp(email,password,this,prog,phone,username,fullname);
    }

    public void defineViews(){
        //Button definition
        btnSignUp = findViewById(R.id.signUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        //EditText Definition
        editTextPassword = findViewById(R.id.password);
        ediTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.phoneEd);
        editTextFullName = findViewById(R.id.fullname);

        //Define TextInputLayout
        textInputLayoutEmail = findViewById(R.id.textInputLayout2);
        textInputLayoutFullName = findViewById(R.id.textInputLayout5);
        textInputLayoutPassword = findViewById(R.id.textInputLayout4);
        textInputLayoutUsername = findViewById(R.id.textInputLayout);
        textInputLayoutPhone = findViewById(R.id.textInputLayout3);


    }

    public void setAnimation(TextInputLayout textInputLayout,long time) {
        textInputLayout.setOnClickListener(v -> realAnime(textInputLayout,time));
    }

    public void setAnimation(EditText editText){
        editText.setOnClickListener(v -> editTextAnime(editText));
    }

    public void setAnimation(Button button){
        button.setOnClickListener(v -> realAnimeBut(btnSignUp));
    }
    public void realAnime(TextInputLayout textInputLayout,long time) {
        YoYo.with(Techniques.Flash)
                .duration(time)
                .repeat(1)
                .playOn(textInputLayout);
    }

    public void realAnimeBut(Button button) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(1)
                .playOn(button);
    }

    public void editTextAnime(EditText editText){
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(1)
                .playOn(editText);
    }

}