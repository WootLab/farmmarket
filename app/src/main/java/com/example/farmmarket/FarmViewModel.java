package com.example.farmmarket;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class FarmViewModel extends AndroidViewModel {
    private FarmRepository repository ;
    private LiveData<List<Farm>> liveFarms;

    public FarmViewModel(@NonNull Application application) {
        super(application);
        repository = FarmRepository.getFarmRepositoryInstance(application);
        liveFarms = repository.getAllFarms();
    }

    public LiveData<List<Farm>> getAllFarms(){
        return liveFarms;
    }

}
