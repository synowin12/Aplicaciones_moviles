package com.movil.eva_grupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        asignarReferencias();
    }

    private void asignarReferencias(){
        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap= googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng peli= new LatLng(-12.026192044206766, -76.92231330210245);
        mMap.addMarker(new MarkerOptions()
                .position(peli)
                .title("Puesto Películas Santa Anita")
                .icon(cambiarIcono(getApplicationContext(), R.drawable.tiendaicon))
                );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(peli, 16));
    }

    private BitmapDescriptor cambiarIcono(Context context, int id) {
        Drawable imagen= ContextCompat.getDrawable(context,id);
        imagen.setBounds(0,0,imagen.getIntrinsicWidth(),imagen.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(imagen.getIntrinsicWidth(), imagen.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        imagen.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }


}