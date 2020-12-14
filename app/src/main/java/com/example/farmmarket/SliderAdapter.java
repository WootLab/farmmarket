package com.example.farmmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] images = {
            R.drawable.add_missing_place,
            R.drawable.search_place,
            R.drawable.sit_back_and_relax,
            R.drawable.make_a_call
    };

    public String[] slide_heading = {
            "SLEEP",
            "EATING",
            "PLAYING"
    };

    public String[] descriptions = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                    "eiusmod tempor incididunt ut labore et dolore magna aliqua. Facilisis magna etiam tempor orci eu.",
            "Eget nulla facilisi etiam dignissim diam. Egestas dui id ornare arcu. Ut morbi tincidunt augue interdum velit euismod.",
            "Euismod in pellentesque massa placerat duis ultricies. Tristique nulla aliquet enim tortor at. Amet consectetur adipiscing " +
                    "elit ut. Facilisis leo vel fringilla est ullamcorper. Facilisis gravida neque convallis a cras semper auctor neque."
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container);

        return view;
        //return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return descriptions.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return false;
    }
}
