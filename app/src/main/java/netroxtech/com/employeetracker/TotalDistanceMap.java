package netroxtech.com.employeetracker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import netroxtech.com.employeetracker.services.GetCurrentLocation;
import netroxtech.com.employeetracker.utils.Constants;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class TotalDistanceMap extends FragmentActivity implements OnMapReadyCallback,OnMarkerDragListener{
    private GoogleMap googleMap;
    double  longitude ;
    double  latitude;
    GetCurrentLocation gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_distance_map);
        setXML();

    }

    public void setXML(){
        gps = new GetCurrentLocation(TotalDistanceMap.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initilizeMap() {
        if (googleMap == null) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // check if map is created successfully or not

        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Muy Location").draggable(true);
        map.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.setOnMarkerDragListener(TotalDistanceMap.this);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        InitilizeSharePref  initilizeSharePref = new InitilizeSharePref(TotalDistanceMap.this);
       double latitude =  marker.getPosition().latitude;
       double longitude = marker.getPosition().longitude;
        initilizeSharePref.removeData(Constants.OFFICELATITUDEPREF);
        initilizeSharePref.removeData(Constants.OFFICELONGITUDEPREF);
       initilizeSharePref.saveDistance(String.valueOf(latitude), String.valueOf(longitude));

        Log.v(Constants.MYLOGS, String.valueOf(latitude));
    }
}

