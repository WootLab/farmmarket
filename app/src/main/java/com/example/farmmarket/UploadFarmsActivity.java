package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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
        String pos = location.getText().toString().trim();
        String imageStr = image.getText().toString().trim();
        String titleStr = title.getText().toString().trim();
        String descriptionStr = description.getText().toString().trim();
        //i stopped here
        double dlat = Double.parseDouble(lat.getText().toString().trim());
        double dltd = Double.parseDouble(ltd.getText().toString().trim());

        Farm farm = new Farm(pos,titleStr,descriptionStr,imageStr,dlat,dltd);
        if(pos.isEmpty()){
            location.setError("Farm location is needed");
            location.requestFocus();
            return;
        }

        if(imageStr.isEmpty()){
            image.setError("farm image is needed");
            image.requestFocus();
            return;
        }

        if(titleStr.isEmpty()){
            title.setError("title cant be empty");
            title.requestFocus();
            return;
        }

        if(descriptionStr.isEmpty()){
            description.setError("Pls describe your farm");
            description.requestFocus();
            return;
        }
        ProgressBar bar = findViewById(R.id.progressBar3);
        FarmRepository.getFarmRepositoryInstance(this).uploadFarm(farm,this,bar);
    }
}