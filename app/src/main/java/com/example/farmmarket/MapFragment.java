package com.example.farmmarket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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
    private View mView;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = mView.findViewById(R.id.mapView);
        if(mapView != null && butMap != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(googleMap -> {
                MapsInitializer.initialize(Objects.requireNonNull(getContext()));
                mMap = googleMap;
                //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                butMap.setOnClickListener(v -> {
                    LatLng hyderadbad = new LatLng(farm.getLat(), farm.getLtd());
                    mMap.addMarker(new MarkerOptions().position(hyderadbad).title(farm.getTitle()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hyderadbad,20));
                });

            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        butMap = mView.findViewById(R.id.buttonMap);

        return mView ;
    }
}