package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btnFarm;
    //private FarmViewModel model;
    private List<Farm> mListOfFarms;
    private FarmAdapter mFarmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //i STOPPED HERE

        btnFarm = findViewById(R.id.buttonFarm);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        btnFarm.setOnClickListener(v->gotoFarmUpload());
        //We stopped here
        mFarmAdapter = new FarmAdapter(this);
        recyclerView.setAdapter(mFarmAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //model = new ViewModelProvider(this).get(FarmViewModel.class);
        FarmViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(FarmViewModel.class);
        viewModel.getAllFarms().observe(MainActivity.this, new Observer<List<Farm>>() {
            @Override
            public void onChanged(List<Farm> farms) {
                mListOfFarms = farms;
                mFarmAdapter.setFarms(farms);
                setEmptyFarm(mListOfFarms);

                Log.d("Flow","This is d last");
                Log.d("MainActivity",""+farms.size());
            }
        });

        String email = mAuth.getCurrentUser().getEmail();
        Log.d("MainActivity",email);
        if(email.equals("bam@gmail.com")||email.equals("chibaba@gmail.com")||email.equals("yvonne@gmail.com")){
            btnFarm.setVisibility(View.VISIBLE);
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