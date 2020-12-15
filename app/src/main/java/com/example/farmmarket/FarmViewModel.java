package com.example.farmmarket;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class FarmViewModel extends AndroidViewModel {
    private final LiveData<List<Farm>> liveFarms;

    public FarmViewModel(@NonNull Application application) {
        super(application);
        FarmRepository repository = FarmRepository.getFarmRepositoryInstance(application);
        liveFarms = repository.getAllFarms();
    }

    public LiveData<List<Farm>> getAllFarms(){
        return liveFarms;
    }

}
