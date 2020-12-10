package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class farmDetail extends AppCompatActivity {

    private ImageView imageView ;
    private TextView textViewName,textViewDesc;
    private Farm farm;
    private Button butMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);
        imageView = findViewById(R.id.imageView4);
        textViewName = findViewById(R.id.textViewName);
        textViewDesc = findViewById(R.id.completedesc);
        butMap = findViewById(R.id.buttonMap);

        farm = (Farm) getIntent().getSerializableExtra(MainActivity.FARM_PORT);
        textViewName.setText(farm.getTitle());
        textViewDesc.setText(farm.getDescription());
        Glide.with(this)
                .asBitmap().centerCrop()
                .load(Uri.parse("https://cdn.pixabay.com/photo/2013/11/23/13/57/barn-216372_960_720.jpg"))
                .into(imageView);

        butMap.setOnClickListener(v->{
            Log.d("FarmDetail","I should go to google Map");
        });
    }
}