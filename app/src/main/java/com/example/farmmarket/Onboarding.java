package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Onboarding extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView[] textView;
    private LinearLayout linearLayout;
    private int mCurrentPage;
    private Button buttonStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = findViewById(R.id.viewpage);
        linearLayout = findViewById(R.id.linearLayoutdot);

        Button skipBut = findViewById(R.id.skipbutton);
        buttonStarted = findViewById(R.id.buttonStart);
        Log.d("Slider","Am in dis Onboarding1");
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDot(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        skipBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSignUp();
            }
        });
        buttonStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSignUp();
            }
        });
    }


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDot(position);
            mCurrentPage = position;

            if(position == 0){
                buttonStarted.setVisibility(View.INVISIBLE);
            }else if(position == 1){
                buttonStarted.setVisibility(View.INVISIBLE);
            }else if(position == 2){
                buttonStarted.setVisibility(View.INVISIBLE);
            }else if(position == 3){
                YoYo.with(Techniques.SlideInRight)
                        .repeat(1)
                        .duration(1000)
                        .playOn(buttonStarted);
                buttonStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void moveToSignUp() {
        Intent intent = new Intent(Onboarding.this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void addDot(int position){
        textView = new TextView[4];
        linearLayout.removeAllViews();
        for(int i = 0 ; i < textView.length ; i++){
            Log.d("Slider","Am in dis Onboarding textView");
            textView[i] = new TextView(this);
            textView[i].setText(Html.fromHtml("&#8226"));
            textView[i].setTextSize(35);
            textView[i].setTextColor(getResources().getColor(R.color.green));
            linearLayout.addView(textView[i]);
        }

        if(textView.length > 0){
            textView[position].setTextColor(getResources().getColor(R.color.armygreen));
        }
    }
}