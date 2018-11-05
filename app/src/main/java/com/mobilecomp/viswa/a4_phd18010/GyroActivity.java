package com.mobilecomp.viswa.a4_phd18010;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GyroActivity extends AppCompatActivity implements SensorEventListener{
    String TAG;
    private  SensorManager sensorManager;
    private  Sensor gyroSensor;
    SensorEventListener gyroSensorListener;
    SensorDBHelper sDBHelper;
    int choice =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        sDBHelper = new SensorDBHelper(this);

         sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroSensor == null) {
            Log.e(TAG, "Proximity sensor not available.");
            finish(); // Close app
        }

        // Create a listener
        gyroSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                Toast.makeText(getApplicationContext(),"Gyroscope Sensor Values is:"+ sensorEvent.values[2],Toast.LENGTH_SHORT).show();
                Log.e(TAG, Float.toString(sensorEvent.values[2]));
                sDBHelper.addData(choice,x,y,z);
                String gyroSensorData = Float.toString(x).concat(Float.toString(y)).concat(Float.toString(z));
                TextView gyroData = findViewById(R.id.sensorGyroData);
                gyroData.setText(gyroSensorData);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register the listener
        sensorManager.registerListener(gyroSensorListener,
                gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

        Button bBack = (Button) findViewById(R.id.buttonGyroBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(gyroSensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();

    }

}
