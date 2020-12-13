package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class UploadFarmsActivity extends AppCompatActivity {

    private TextInputEditText location, image,title, description;
    private Button btnUploadFarm;
    private EditText lat,ltd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_farms);
        viewDeclaration();
        btnUploadFarm.setOnClickListener(v -> moveFarmsOnline());
    }

    public void viewDeclaration(){
        location = findViewById(R.id.locationEd);
        image = findViewById(R.id.imageEd);
        title = findViewById(R.id.titleEd);
        description = findViewById(R.id.descEd);
        lat = findViewById(R.id.latEdit);
        ltd = findViewById(R.id.lngEdt);
        btnUploadFarm = findViewById(R.id.uploadFarm);
    }

    public void moveFarmsOnline(){
        String pos = Objects.requireNonNull(location.getText()).toString().trim();
        String imageStr = Objects.requireNonNull(image.getText()).toString().trim();
        String titleStr = Objects.requireNonNull(title.getText()).toString().trim();
        String descriptionStr = Objects.requireNonNull(description.getText()).toString().trim();

        Farm farm = new Farm(pos,titleStr,descriptionStr,imageStr);
        if(pos.isEmpty()){
            location.setError("Farm location is needed");
            location.requestFocus();
        }else if(imageStr.isEmpty()){
            image.setError("farm image is needed");
            image.requestFocus();
        } else if(titleStr.isEmpty()){
            title.setError("title cant be empty");
            title.requestFocus();
        } else if(descriptionStr.isEmpty()){
            description.setError("Pls describe your farm");
            description.requestFocus();
        } else{
            try{
                double dlat = Double.parseDouble(lat.getText().toString().trim());
                double dltd = Double.parseDouble(ltd.getText().toString().trim());
                farm.setLat(dlat);
                farm.setLtd(dltd);
                ProgressBar bar = findViewById(R.id.progressBar3);
                FarmRepository.getFarmRepositoryInstance(this).uploadFarm(farm,this,bar);
            }catch (NumberFormatException e){
                Toast.makeText(this,"latitude and longitude must be a number",Toast.LENGTH_LONG)
                        .show();

            }

        }


    }
}