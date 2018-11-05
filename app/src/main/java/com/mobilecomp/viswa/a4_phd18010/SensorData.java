package com.mobilecomp.viswa.a4_phd18010;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SensorData extends AppCompatActivity implements SensorDataFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.sensorFragLayout);
        if (frag == null){
            frag = new SensorDataFragment();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.sensorFragLayout,frag);
        ft.commit();
    }
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
