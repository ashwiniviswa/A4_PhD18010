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

public class ProxyActivity extends AppCompatActivity implements SensorEventListener {
    String TAG;
    int choice = 5;
    private  SensorManager sensorManager;
    private  Sensor proxySensor;
    SensorEventListener proxySensorListener;
    SensorDBHelper sDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        sDBHelper = new SensorDBHelper(this);

        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        proxySensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proxySensor == null) {
            Log.e(TAG, "Proximity sensor not available.");
            finish(); // Close app
        }

        // Create a listener
        proxySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = 0;
                float z = 0;
                if(sensorEvent.values[0] < proxySensor.getMaximumRange()) {
                    // Detected something nearby
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                } else {
                    // Nothing is nearby
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                Toast.makeText(getApplicationContext(),"Proximity Sensor Values is:"+ sensorEvent.values[0],Toast.LENGTH_SHORT).show();
                String ProxySensorData = Float.toString(sensorEvent.values[0]);
                Log.e(TAG,ProxySensorData );
                TextView proxyData = findViewById(R.id.sensorProxyData);
                proxyData.setText(ProxySensorData);
                sDBHelper.addData(choice,x,y,z);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register the listener
        sensorManager.registerListener(proxySensorListener,
                proxySensor, SensorManager.SENSOR_DELAY_NORMAL);

        Button bBack = (Button) findViewById(R.id.buttonProxyBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(proxySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
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
        sensorManager.registerListener(this, proxySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();

    }

}
