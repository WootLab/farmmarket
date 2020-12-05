package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvUpload;
    private Button btnFarm;
    private FarmViewModel model;
    private List<Farm> mListOfFarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUpload = findViewById(R.id.upload);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //i STOPPED HERE

        btnFarm = findViewById(R.id.buttonFarm);
        btnFarm.setOnClickListener(v->gotoFarmUpload());
        //We stopped here
        model = new ViewModelProvider(this).get(FarmViewModel.class);
        model.getAllFarms().observe(MainActivity.this, new Observer<List<Farm>>() {
            @Override
            public void onChanged(List<Farm> farms) {
                mListOfFarms = farms;
            }
        });

        String email = mAuth.getCurrentUser().getEmail();
        User user = new User(email);
        if(FarmRepository.getFarmRepositoryInstance(getApplicationContext()).isAdmin(user)){
            tvUpload.setVisibility(View.VISIBLE);
        }



    }

    private void gotoFarmUpload() {
        Intent intent = new Intent(MainActivity.this,UploadFarmsActivity.class);
        startActivity(intent);
    }


}