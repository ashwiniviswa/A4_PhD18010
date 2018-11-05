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

public class OrientActivity extends AppCompatActivity implements SensorEventListener{
    String TAG;
    int choice =3;
    private  SensorManager sensorManager;
    private  Sensor orientSensor;
    SensorEventListener orientSensorListener;
    SensorDBHelper sDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orient);
        sDBHelper = new SensorDBHelper(this);

        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        orientSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if(orientSensor == null) {
            Log.e(TAG, "Proximity sensor not available.");
            finish(); // Close app
        }

        // Create a listener
        orientSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];
                    String orientValueString = Float.toString(x).concat(Float.toString(y)).concat(Float.toString(z));
                    Toast.makeText(getApplicationContext(), "Orientation Sensor Values is:" + orientValueString, Toast.LENGTH_SHORT).show();


                    Log.e(TAG,orientValueString );
                    TextView orientData = findViewById(R.id.sensorOrientData);
                    orientData.setText(orientValueString);
                    sDBHelper.addData(choice,x,y,z);


                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register the listener
        sensorManager.registerListener(orientSensorListener,
                orientSensor, SensorManager.SENSOR_DELAY_NORMAL);

        Button bBack = (Button) findViewById(R.id.buttonOrientBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(orientSensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
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
        sensorManager.registerListener(this, orientSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();

    }

}
