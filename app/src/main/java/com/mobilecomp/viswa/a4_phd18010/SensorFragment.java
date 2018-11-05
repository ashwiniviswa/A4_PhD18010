package com.mobilecomp.viswa.a4_phd18010;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.hardware.SensorEvent;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment {


    int sensorChoice;
    private OnFragmentInteractionListener mListener;

    public SensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
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
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);


        Button bAcc = view.findViewById(R.id.bAcc);
        Button bGyro = view.findViewById(R.id.bGyro);
        Button bOrient = view.findViewById(R.id.bOrient);
        Button bGPS = view.findViewById(R.id.bGPS);
        Button bProx = view.findViewById(R.id.bProx);


        bAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sensorChoice = 1;
                Bundle b = new Bundle();
                b.putInt("SensorChoice",sensorChoice);
                Intent intent = new Intent(getActivity(), SensorData.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        bGyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorChoice = 2;
                Bundle b = new Bundle();
                b.putInt("SensorChoice",sensorChoice);
                Intent intent = new Intent(getActivity(), GyroActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });
        bOrient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorChoice = 3;
                Bundle b = new Bundle();
                b.putInt("SensorChoice",sensorChoice);

                Intent intent = new Intent(getActivity(), OrientActivity.class);
                intent.putExtras(b);
                startActivity(intent);


            }
        });
        bGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorChoice = 4;
                Bundle b = new Bundle();
                b.putInt("SensorChoice",sensorChoice);
               Intent intent = new Intent(getActivity(), GPSActivity.class);
                intent.putExtras(b);
               startActivity(intent);

            }
        });
        bProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorChoice = 5;
                Bundle b = new Bundle();
                b.putInt("SensorChoice",sensorChoice);
                Intent intent = new Intent(getActivity(), ProxyActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });



        return view;
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
