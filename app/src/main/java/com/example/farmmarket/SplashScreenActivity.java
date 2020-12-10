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

    private static int SPLASH_SCREEN = 5000;

//Yvonne likes to sleep

    Animation topAnimation, bottomAnimation;
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        image = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);

        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}