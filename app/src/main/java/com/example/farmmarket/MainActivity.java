package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private FarmAdapter mFarmAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUpload = findViewById(R.id.upload);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //i STOPPED HERE

        btnFarm = findViewById(R.id.buttonFarm);
        recyclerView = findViewById(R.id.recyclerView);
        btnFarm.setOnClickListener(v->gotoFarmUpload());
        //We stopped here
        mFarmAdapter = new FarmAdapter();
        recyclerView.setAdapter(mFarmAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        model = new ViewModelProvider(this).get(FarmViewModel.class);
        model.getAllFarms().observe(MainActivity.this, new Observer<List<Farm>>() {
            @Override
            public void onChanged(List<Farm> farms) {
                mListOfFarms = farms;
                mFarmAdapter.setFarms(farms);
                setEmptyFarm(mListOfFarms);
            }
        });

        String email = mAuth.getCurrentUser().getEmail();
        User user = new User(email);
        if(FarmRepository.getFarmRepositoryInstance(getApplicationContext()).isAdmin(user)){
            tvUpload.setVisibility(View.VISIBLE);
        }

    }


    public void setEmptyFarm(List<Farm> farms){
        TextView textView = findViewById(R.id.emptyTV);
        if(farms.size() > 0){
            textView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void gotoFarmUpload() {
        Intent intent = new Intent(MainActivity.this,UploadFarmsActivity.class);
        startActivity(intent);
    }


}