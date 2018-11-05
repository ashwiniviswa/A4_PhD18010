package com.mobilecomp.viswa.a4_phd18010;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainLayout);
        if (frag == null){
            frag = new SensorFragment();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.mainLayout,frag);
        ft.commit();
    }
    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

}
