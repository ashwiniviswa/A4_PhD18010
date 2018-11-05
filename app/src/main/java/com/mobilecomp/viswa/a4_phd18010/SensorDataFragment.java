package com.mobilecomp.viswa.a4_phd18010;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorDataFragment extends Fragment implements SensorEventListener {
    String TAG;
    int choice = 1;


    SensorManager sensorManager;
    Sensor accSensor;
    SensorEventListener accSensorListener;
    private float acc;
    private float currentAcc; // current acceleration including gravity
    private float lastAcc; // last acceleration including gravity
    SensorDBHelper sDBHelper;


    private OnFragmentInteractionListener mListener;

    public SensorDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorDataFragment newInstance(String param1, String param2) {
        SensorDataFragment fragment = new SensorDataFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensor_data, container, false);

        sDBHelper = new SensorDBHelper(getContext());
        sensorManager =
                (SensorManager) getActivity().getSystemService (getActivity().SENSOR_SERVICE);
        Toast.makeText(getContext(),"Sensor Choice is Accelerometer",Toast.LENGTH_SHORT).show();



        Button bBack = (Button) view.findViewById(R.id.buttonBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sensorManager.unregisterListener(accSensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();


                accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


                if (accSensor == null) {

                    Log.e(TAG, "Accelerometer sensor not available.");
                    getActivity().finish(); // Close app
                }


                // Create listener
                accSensorListener = new SensorEventListener() {
                //proxySensorListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                            float x = sensorEvent.values[0];
                            float y = sensorEvent.values[1];
                            float z = sensorEvent.values[2];
                            String acceValueString = Float.toString(x).concat(Float.toString(y)).concat(Float.toString(z));
                            Toast.makeText(getContext(), "Accelerometer Sensor Values is:" + acceValueString, Toast.LENGTH_SHORT).show();

                            lastAcc = currentAcc;
                            currentAcc = (float) Math.sqrt((double) (x * x + y * y + z * z));
                            float delta = currentAcc - lastAcc;
                            acc = acc * 0.9f + delta;

                            if (acc > 11) {
                                Toast.makeText(getContext(), "Shake Detected", Toast.LENGTH_SHORT).show();
                            }

                           Log.e(TAG, acceValueString);



                            sDBHelper.addData(choice,x,y,z);
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {
                    }
                };

                // Register it, specifying the polling interval in
                // microseconds
               sensorManager.registerListener(accSensorListener,accSensor, 2 * 1000 * 1000);
               sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);






    }


    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        //sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

