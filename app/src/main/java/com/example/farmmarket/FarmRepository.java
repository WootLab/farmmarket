package com.example.farmmarket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class FarmRepository {

    private static  final String NODE_USERS = "users";
    public static final String NODE_FARMS = "farms";
    //private StorageReference mStorageRef;
    private final DatabaseReference mDatabaseRef;
    private final DatabaseReference mDatabaseRefUsers;
    private static FarmRepository farmRepository;
    private final  FirebaseAuth mAuth;
    private final LiveData<List<Farm>> livefarms;

    private FarmRepository(Context context){
        //mStorageRef = FirebaseStorage.getInstance().getReference("farms");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(NODE_FARMS);
        mDatabaseRefUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        mAuth =FirebaseAuth.getInstance();
        FetchFromBase fetchFromBase = FetchFromBase.getInstanceOfFireBase(context);
        livefarms = fetchFromBase.getAllFarms();
    }

    public static FarmRepository getFarmRepositoryInstance(Context context){
        if(farmRepository == null){
            farmRepository = new FarmRepository(context);
            return  farmRepository;
        }
        return  farmRepository;
    }


    //Allow the admin to upload a farm
    public void uploadFarm(Farm farm, Context context,ProgressBar bar){
        if(farm != null){
            bar.setVisibility(View.VISIBLE);
            String farmId = mDatabaseRef.push().getKey();
            assert farmId != null;
            mDatabaseRef.child(farmId)
                   .setValue(farm)
                   .addOnFailureListener(e -> {
                       Log.d("FarmRepo",e.getMessage());
                       Toast.makeText(context,"Farm could not be aadded",Toast.LENGTH_LONG).show();
                   })
                   .addOnCompleteListener((Task<Void> task) -> {
                       if(task.isSuccessful()){
                           bar.setVisibility(View.GONE);
                           Toast.makeText(context, "Succesfully entered in the base ", Toast.LENGTH_LONG)
                                   .show();
                       }
                   });
        }
    }



    public void signUp(String email, String password, Context context, String codeName, ProgressBar bar){
        bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnFailureListener(e -> {
                    Log.d("RepoFailure",password);
                    Log.d("RepoFailure",email);
                    Log.d("RepoFailure",codeName);

                    Toast.makeText(context,"error"+e.getMessage(),Toast.LENGTH_LONG).show();
                    bar.setVisibility(View.GONE);
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            bar.setVisibility(View.GONE);
                            Toast.makeText(context, "You have signed up", Toast.LENGTH_LONG).show();
                            //This is where we set the values we want our users to have
                            User user = new User(email);
                            user.setEmail(email);
                            user.setCodeName(codeName);
                            user.setPassword(password);
                            //Then add the user to ur database
                            String userId = mDatabaseRef.push().getKey();
                            assert userId != null;
                            mDatabaseRefUsers.child(userId)
                                    .setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            login(email, password, context,bar);
                                        }
                                        
                                        if(!task.isSuccessful()){
                                            Toast.makeText(context,"not working",Toast.LENGTH_LONG).show();
                                            Log.d("Repo","Not complete");
                                            bar.setVisibility(View.GONE);
                                        }

                                        if (task1.isCanceled()) {
                                            Toast.makeText(context, "action was cancelled", Toast.LENGTH_LONG).show();
                                            bar.setVisibility(View.GONE);
                                        }
                                    });

                        }
                        
                        

                        if (task.isCanceled()) {
                            Toast.makeText(context, "Process was cancelled", Toast.LENGTH_LONG).show();
                            bar.setVisibility(View.GONE);
                        }
                    }
                });


    }

    public void login(String email,String password,Context context,ProgressBar bar){
        bar.setVisibility(View.VISIBLE);
       mAuth.signInWithEmailAndPassword(email, password)
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                       Log.d("Login error",e.getMessage());
                       bar.setVisibility(View.GONE);
                   }
               })
               .addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       //Go to the next Activity
                       bar.setVisibility(View.GONE);
                       Intent intent = new Intent(context,MainActivity.class);
                       context.startActivity(intent);
                   }
               });
    }

    public boolean isAdmin(User user){
        return user.getIsAdmin();
    }

    public LiveData<List<Farm>> getAllFarms(){
        return livefarms;
    }


}
