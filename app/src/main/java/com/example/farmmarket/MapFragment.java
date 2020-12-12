package com.example.farmmarket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private Button butMap;
    private GoogleMap mMap;
    private Farm farm;
    private static final String FARM = "FarmMap";
    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(Farm farm) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable(FARM,farm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        farm = (Farm) getArguments().getSerializable(FARM);
        Log.d("MapFragment",farm.getLocation());
        /*SupportMapFragment supportMapFragment = (SupportMapFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            butMap.setOnClickListener(v->{
                if(mMap != null){
                    LatLng hyderadbad = new LatLng(farm.getLat(), farm.getLtd());
                    mMap.addMarker(new MarkerOptions().position(hyderadbad).title(farm.getTitle()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hyderadbad,20));
                }

            });
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        butMap = view.findViewById(R.id.buttonMap);
        /*SupportMapFragment supportMapFragment = (SupportMapFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            butMap.setOnClickListener(v->{
                if(mMap != null){
                    LatLng hyderadbad = new LatLng(farm.getLat(), farm.getLtd());
                    mMap.addMarker(new MarkerOptions().position(hyderadbad).title(farm.getTitle()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hyderadbad,20));
                }

            });
        });*/

        return view ;
    }
}