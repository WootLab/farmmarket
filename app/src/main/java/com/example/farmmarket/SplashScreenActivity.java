package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {


//Yvonne likes to sleep

    Animation topAnimation, bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        ImageView image = findViewById(R.id.imageView);
        TextView text = findViewById(R.id.textView);

        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(() -> {
            boolean isFirstTime = AccSharedPref.getUserState(this);

            if(isFirstTime){
                AccSharedPref.setUserState(this,false);
                startActivity(new Intent(SplashScreenActivity.this,Onboarding.class));
                finish();
            } else{
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN);
    }
}