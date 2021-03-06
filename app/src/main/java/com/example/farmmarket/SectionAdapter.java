package com.example.farmmarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionAdapter extends FragmentPagerAdapter {
    Farm farm;
    public SectionAdapter(@NonNull FragmentManager fm,Farm farm) {
        super(fm);
        this.farm = farm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return DescriptionFragment.newInstance(farm);
            case 1:
                return MapFragment.newInstance(farm);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Description";
            case 1:
                return "Check on map";

        }


        return super.getPageTitle(position);
    }
}
