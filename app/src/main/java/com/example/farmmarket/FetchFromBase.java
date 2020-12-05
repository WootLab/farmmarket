package com.example.farmmarket;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FetchFromBase {
    private static FetchFromBase sfirebaseDatabase;
    private final MutableLiveData<List<Farm>> livefarms;
    private final List<Farm> farmList;
    private final DatabaseReference firebaseDatabase;
    private final Context mContext;


    private FetchFromBase(Context context){
        livefarms = new MutableLiveData<>();
        mContext = context.getApplicationContext();
        farmList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference(FarmRepository.NODE_FARMS);
    }


    public static FetchFromBase getInstanceOfFireBase(Context context){
        if(sfirebaseDatabase == null){
            sfirebaseDatabase = new FetchFromBase(context);
            return sfirebaseDatabase;
        }

        return sfirebaseDatabase;

    }


    public void loadData(){
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Farm farm = dataSnapshot.getValue(Farm.class);
                    Log.d("Base",""+farm);
                    farmList.add(farm);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public LiveData<List<Farm>> getAllFarms(){
        loadData();//The load data might be here so it can always load the new data for us
        livefarms.setValue(farmList);
        return  livefarms;
    }
}
