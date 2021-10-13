package com.example.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GmapsLocation extends AppCompatActivity implements OnMapReadyCallback {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";
    public static final String SEND_ATTRACTION_ID = "com.example.mcslecproject.SEND_ATTRACTION_ID";

    int userId;
    int attractionId;

    private GoogleMap mapForm;

    private TextView longitudeMap;
    private TextView latitudeMap;

    String strLongitude;
    String strLatitude;
    String atName;

    Double dLongitude;
    Double dLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmaps_location);

//        longitudeMap = findViewById(R.id.textViewLongitudeMapDisplay);
//        latitudeMap = findViewById(R.id.textViewLatitudeMapDisplay);

        Intent intent = getIntent();
        userId = intent.getIntExtra(SEND_ID, -1);
        attractionId = Integer.parseInt(intent.getStringExtra(SEND_ATTRACTION_ID));

        strLongitude = HomeForm.locations.get(attractionId).getAttractionLongitude();
        strLatitude = HomeForm.locations.get(attractionId).getAttractionLatitude();
        atName = HomeForm.locations.get(attractionId).getAttractionName();

//        longitudeMap.setText(strLongitude);
//        latitudeMap.setText(strLatitude);

        dLongitude = Double.parseDouble(strLongitude);
        dLatitude = Double.parseDouble(strLatitude);

        SupportMapFragment fragment = SupportMapFragment.newInstance();
        fragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_mapform, fragment).commit();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapForm = googleMap;

        LatLng GeoPos = new LatLng(dLatitude, dLongitude);
        mapForm.addMarker(new MarkerOptions().position(GeoPos).title(atName)).showInfoWindow();
        mapForm.moveCamera(CameraUpdateFactory.newLatLngZoom(GeoPos, 10));

    }

}