package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView tvUpload;
    private Button btnFarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUpload = findViewById(R.id.upload);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //i STOPPED HERE

        if(FarmRepository.getFarmRepositoryInstance().isAdmin( new User(mAuth.getCurrentUser().getEmail()))){
            tvUpload.setVisibility(View.VISIBLE);
        }


        btnFarm = findViewById(R.id.buttonFarm);
        btnFarm.setOnClickListener(v->gotoFarmUpload());

    }

    private void gotoFarmUpload() {
        Intent intent = new Intent(MainActivity.this,UploadFarmsActivity.class);
        startActivity(intent);
    }


}