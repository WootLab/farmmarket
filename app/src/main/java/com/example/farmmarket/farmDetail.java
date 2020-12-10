package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class farmDetail extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView imageView ;
    private TextView textViewName,textViewDesc;
    private Farm farm;
    private Button butMap;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);
        imageView = findViewById(R.id.imageView4);
        textViewName = findViewById(R.id.textViewName);
        textViewDesc = findViewById(R.id.completedesc);
        butMap = findViewById(R.id.buttonMap);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        farm = (Farm) getIntent().getSerializableExtra(MainActivity.FARM_PORT);
        textViewName.setText(farm.getTitle());
        textViewDesc.setText(farm.getDescription());
        Glide.with(this)
                .asBitmap().centerCrop()
                .load(Uri.parse("https://cdn.pixabay.com/photo/2013/11/23/13/57/barn-216372_960_720.jpg"))
                .into(imageView);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in hyderabad and move the camera
        butMap.setOnClickListener(v->{
            if(mMap != null){
                LatLng hyderadbad = new LatLng(17, 78);
                mMap.addMarker(new MarkerOptions().position(hyderadbad).title("Garri farm"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hyderadbad,20));
            }

        });

    }
}