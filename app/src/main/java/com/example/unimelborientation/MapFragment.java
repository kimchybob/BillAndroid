package com.example.unimelborientation;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap mMap;
    private Button location;
    private Spinner spinner;
    LatLng center = new LatLng(-37.79834,144.960973);
    LatLng baillieu = new LatLng(-37.798475,144.959358);
    LatLng oldEngineering = new LatLng(-37.799476,144.961478);
    LatLng giblinEunson = new LatLng(-37.801231,144.95946);
    LatLng stopOne = new LatLng(-37.799902,144.963771);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.locations, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

//        location =(Button)view.findViewById(R.id.library);
//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMap.addMarker(new MarkerOptions()
//                        .position(baillieu)
//                        .title("baillieu library"));
//            }
//        });

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.basicMap);

        supportMapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap){
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
//                mMap.addMarker(new MarkerOptions()
//                        .position(mel)
//                        .title("Marker in Sydney"));
//                mMap.animateCamera(CameraUpdateFactory.newLatLng(mel));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center,16));
            }
        });
        return view;
    }

    public void addMarker(String name){
        mMap.clear();
        LatLng location = name.equals("Baillieu Library")?baillieu:
                name.equals("Old Engineering Building")?oldEngineering:
                name.equals("Giblin Eunson Library")?giblinEunson:
                name.equals("Stop 1")?stopOne:null;
        if(location==null){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center,16));
            return;
        }
        mMap.addMarker(new MarkerOptions()
            .position(location)
            .title(name));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
        addMarker(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}