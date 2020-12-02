package com.example.farmmarket;

import android.app.Application;
import android.content.Context;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class FarmViewModel extends AndroidViewModel {
    private FarmRepository repository ;
    public FarmViewModel(@NonNull Application application) {
        super(application);
        repository = FarmRepository.getFarmRepositoryInatance();
    }



    public void uploadFarms(Farm farm, Context context){
        repository.uploadFarm(farm,context);
    }

    public void joinUp(String email, String password){
        repository.signUp(email,password);
    }
}
