package com.example.farmmarket;

import android.app.Application;
import android.content.Context;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class FarmViewModel extends AndroidViewModel {
    private FarmRepository repository ;
    private MutableLiveData<Farm> liveFarms;
    public FarmViewModel(@NonNull Application application) {
        super(application);
        repository = FarmRepository.getFarmRepositoryInstance();
    }

}
