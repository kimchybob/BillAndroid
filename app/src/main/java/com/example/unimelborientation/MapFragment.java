package com.example.unimelborientation;

import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


//import com.example.unimelborientation.R;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Location location;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        location = task.getResult();
                        if (location != null) {
                            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(@NonNull GoogleMap googleMap) {
                                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    MarkerOptions options = new MarkerOptions().position(latLng).title("I am here");
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
                                    googleMap.addMarker(options);
                                }
                            });
                        } else {
                            LocationRequest locationRequest = new LocationRequest()
                                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                    .setInterval(10000)
                                    .setFastestInterval(1000)
                                    .setNumUpdates(1);

                            LocationCallback locationCallback = new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    location = locationResult.getLastLocation();
                                }
                            };

                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                            }
                        }
                    }
                });
            }
        }

//            Task<Location> task = client.getLastLocation();
//            task.addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if(location != null){
//                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//                            @Override
//                            public void onMapReady(@NonNull GoogleMap googleMap) {
//                                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
//                                MarkerOptions options = new MarkerOptions().position(latLng).title("I am here");
//                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,8));
//                                googleMap.addMarker(options);
//                            }
//                        });
//                    }
//                }
//            });
//        }
//        else{
//            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
//        }

//        supportMapFragment.getMapAsync(new OnMapReadyCallback(){
//            @Override
//            public void onMapReady(@NonNull GoogleMap googleMap){
//                mMap = googleMap;
//
//                // Add a marker in Sydney and move the camera
//                LatLng mel = new LatLng(-37, 145);
//                mMap.addMarker(new MarkerOptions()
//                        .position(mel)
//                        .title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(mel));
//                mMap.moveCamera(CameraUpdateFactory.zoomTo(8));
////
////                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
////                    @Override
////                    public void onMapClick(@NonNull LatLng latLng) {
////                        MarkerOptions markerOptions = new MarkerOptions();
////                        markerOptions.position(latLng);
////                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
////                        googleMap.clear();
////                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
////                                latLng,3
////                        ));
////                        googleMap.addMarker(markerOptions);
////                    }
////                });
//            }
//        });
        return view;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        binding = ActivityMapsBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
//        setContentView(R.layout.map_fragment);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
}