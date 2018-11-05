package com.mobilecomp.viswa.a4_phd18010;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GPSActivity extends AppCompatActivity {


    private LocationManager locationManager;
    private LocationListener locationListener;
    TextView gpsData;
    String GPSValueString;
    SensorDBHelper sDBHelper;
    int choice = 4;
    String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        gpsData = findViewById(R.id.sensorGPSData);
        sDBHelper = new SensorDBHelper(this);

        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        //locationListener = new GPSLocationListener();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                gpsData.append("\n LAT:"+location.getLatitude()+"LON"+location.getLongitude());
                float x = (float)location.getLatitude();
                float y = (float)location.getLongitude();
                float z = 0;

                GPSValueString =Float.toString(x).concat(Float.toString(y));

                Toast.makeText(getApplicationContext(), "GPS  Values is:" + GPSValueString, Toast.LENGTH_SHORT).show();
                Log.e(TAG,GPSValueString);
                sDBHelper.addData(choice,x,y,z);


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            }
        }else{
            configureButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureButton();
                    return;
                }
        }
    }

    private void configureButton() {
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }


    /*private class GPSLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(),
                        "Location updated to : Latitude: " + loc.getLatitude() + " Longitude: " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            // TODO Auto-generated method stub
        }
    }*/
        }