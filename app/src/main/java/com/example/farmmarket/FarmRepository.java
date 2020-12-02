package com.example.farmmarket;

import android.content.Context;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FarmRepository {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private static FarmRepository farmRepository;
    private FirebaseAuth mAuth;




    private FarmRepository(){
        mStorageRef = FirebaseStorage.getInstance().getReference("farms");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("farms");
        mAuth =FirebaseAuth.getInstance();
    }

    public static FarmRepository getFarmRepositoryInatance(){
        if(farmRepository == null){
            farmRepository = new FarmRepository();
            return  farmRepository;
        }
        return  farmRepository;
    }





    public void uploadFarm(Farm farm, Context context){
        if(farm != null){
           mDatabaseRef.child(System.currentTimeMillis()+farm.getName())
                   .setValue(farm)
                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(context,"Succesfully entered in the base ",Toast.LENGTH_LONG).show();
                       }
                   });


        }else{
            Toast.makeText(context,"No farm was found",Toast.LENGTH_LONG).show();
        }

    }

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Go to congratylations page

                        }
                    }
                });
    }
}
