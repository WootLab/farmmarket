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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView imageView ;
    private TextView textViewName,textViewDesc;
   // private Farm farm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(Farm farm) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable("Farm",farm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //farm = (Farm) getArguments().getSerializable(FarmDetailFragmentActivity.FARM_ID);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        /*imageView = view.findViewById(R.id.imageView4);
        textViewName = view.findViewById(R.id.textViewName);
        textViewDesc = view.findViewById(R.id.completedesc);

        textViewName.setText("Title");
        textViewDesc.setText("description");
        Glide.with(this)
                .asBitmap().centerCrop()
                .load(Uri.parse("https://cdn.pixabay.com/photo/2013/11/23/13/57/barn-216372_960_720.jpg"))
                .into(imageView);*/
        return view;
    }
}