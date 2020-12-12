package com.example.farmmarket;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DescriptionFragment extends Fragment {

    private Farm farm;
    private static final String FARMDES = "Farmdesc";
    public DescriptionFragment() {
        // Required empty public constructor
    }


    public static DescriptionFragment newInstance(Farm farm) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable(FARMDES,farm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        farm = (Farm) getArguments().getSerializable(FARMDES);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        TextView textViewName = view.findViewById(R.id.textView3);
        TextView textLocation = view.findViewById(R.id.textlocation);
        TextView textViewDesc = view.findViewById(R.id.textContent);

        textViewName.setText(farm.getTitle());
        textLocation.setText(farm.getLocation());
        textViewDesc.setText(farm.getDescription());

        return view;
    }
}