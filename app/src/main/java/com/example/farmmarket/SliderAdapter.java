package com.example.farmmarket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] images = {
            R.drawable.construction_worker_isometric,
            R.drawable.it_support_isometric,
            R.drawable.military_isometric,
            R.drawable.watering_plant_isometric
    };

    public String[] slide_heading = {
            "CLEARING",
            "CUSTOMER CARE",
            "SECURITY",
            "NURSING"
    };

    public String[] descriptions = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                    "eiusmod tempor incididunt ut labore et dolore magna aliqua. Facilisis magna etiam tempor orci eu.",
            "Eget nulla facilisi etiam dignissim diam. Egestas dui id ornare arcu. Ut morbi tincidunt augue interdum velit euismod.",
            "Euismod in pellentesque massa placerat duis ultricies. Tristique nulla aliquet enim tortor at. Amet consectetur adipiscing " +
                    "elit ut. Facilisis leo vel fringilla est ullamcorper. Facilisis gravida neque convallis a cras semper auctor neque.",
            "Euismod in pellentesque massa placerat duis ultricies. Tristique nulla aliquet enim tortor at. Amet consectetur adipiscing " +
            "elit ut. Facilisis leo vel fringilla est ullamcorper. Facilisis gravida neque convallis a cras semper auctor neque.",

    };

    public SliderAdapter(Context context) {
        Log.d("Slider","Am in dis Onboarding2");
        this.context = context;
    }


   @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container,false);
        ImageView imageView = view.findViewById(R.id.imageViewSlider);
        TextView textViewSlidHead = view.findViewById(R.id.textViewHeaderSlider);
        TextView textViewDesc = view.findViewById(R.id.textViewDescSlid);
        textViewDesc.setText(descriptions[position]);
        textViewSlidHead.setText(slide_heading[position]);
        Glide.with(context)
                .asBitmap()
                .load(images[position])
                .into(imageView);

        container.addView(view);
        return view;

    }

    @Override
    public int getCount() {
        return descriptions.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }


}
