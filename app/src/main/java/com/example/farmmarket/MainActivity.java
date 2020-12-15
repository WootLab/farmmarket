package com.example.farmmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //private FarmViewModel model;
    private List<Farm> mListOfFarms;
    private FarmAdapter mFarmAdapter;
    public static final String FARM_PORT =  "com.example.farmmarket.farm_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //i STOPPED HERE

        Button btnFarm = findViewById(R.id.buttonFarm);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ProgressBar bar = findViewById(R.id.progressBar5);
        btnFarm.setOnClickListener(v->gotoFarmUpload());
        //We stopped here
        mFarmAdapter = new FarmAdapter(this);
        recyclerView.setAdapter(mFarmAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //model = new ViewModelProvider(this).get(FarmViewModel.class);
        FarmViewModel viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(FarmViewModel.class);
        viewModel.getAllFarms().observe(MainActivity.this, farms -> {
            mListOfFarms = farms;
            if(!FarmRepository.getFarmRepositoryInstance(MainActivity.this).getLoading()){
                bar.setVisibility(View.GONE);
            }

            mFarmAdapter.setFarms(farms);
            setEmptyFarm(mListOfFarms);

        });

        String email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        Log.d("MainActivity",email);
        assert email != null;
        if(email.equals("bam@gmail.com")||email.equals("chibaba@gmail.com")||email.equals("yvonne@gmail.com")){
            btnFarm.setVisibility(View.VISIBLE);
        }

        mFarmAdapter.farmDetail(position -> {
            Farm farm = mListOfFarms.get(position);
            Intent intent = new Intent(MainActivity.this,FarmDetailFragmentActivity.class);
            intent.putExtra(FARM_PORT,farm);
            startActivity(intent);
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        int positionOfMenuItem = 0; // or whatever...
        MenuItem item = menu.getItem(positionOfMenuItem);
        SpannableString s = new SpannableString("Logout");
        s.setSpan(new ForegroundColorSpan(Color.GREEN), 0, s.length(), 0);
        item.setTitle(s);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FarmRepository.getFarmRepositoryInstance(this).logOut(this);
            return true;
        }
        return super.onOptionsItemSelected(item);

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