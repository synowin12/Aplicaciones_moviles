package com.movil.eva_grupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class mapa2Activity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    GoogleMap mMap;
    FusedLocationProviderClient  fusedLocationProviderClient;
    double latitudActual=0, longitudActual=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa2);
        asignarReferencias();
    }
    private void asignarReferencias(){
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa2);
        getUbicacionActual();
    }
    private void getUbicacionActual(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

        }else{
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            Task<Location> tarea = fusedLocationProviderClient.getLastLocation();
            tarea.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        latitudActual = location.getLatitude();
                        longitudActual = location.getLongitude();

                    }
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            mMap = googleMap;
                            mMap.getUiSettings().setZoomControlsEnabled(true);

                            LatLng posicionActual = new LatLng(latitudActual, longitudActual);
                            mMap.addMarker(new MarkerOptions()
                                    .position(posicionActual)
                                    .title("Tú Posición Actual")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicionActual, 16));
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        if(requestCode==44){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getUbicacionActual();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}