package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    TextView tvx, tvy, tvz, tvDir, tvAccur, tvSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            setContentView(R.layout.activity_main);
            tvx=findViewById(R.id.tvX);
            tvy=findViewById(R.id.tvY);
            tvz=findViewById(R.id.tvZ);
            tvDir=findViewById(R.id.tvDirection);
            tvAccur=findViewById(R.id.tvAccuracy);
            tvSel=findViewById(R.id.tvSelection);


            // choose the sensor you want
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int a = event.accuracy;

            if (Math.abs(x) > 0.1f) {
                tvx.setText(String.valueOf(x));
                tvAccur.setText(String.valueOf(a));
            }

            if (Math.abs(y) > 0.1f) {
                tvy.setText(String.valueOf(y));
                tvAccur.setText(String.valueOf(a));
            }
            if (Math.abs(z) > 0.1f) {
                tvz.setText(String.valueOf(z));
                tvAccur.setText(String.valueOf(a));
            }

            if (x < 0.2f & x >0f  ) {
                tvDir.setText("North tilt");
                tvSel.setText("X was " + String.valueOf(x));
            } else if (x > 0.8f ) {
                tvDir.setText("South tilt");
                tvSel.setText("X was " + String.valueOf(x));
            }

            if (y > 0.35f ) {
                tvDir.setText("East tilt");
                tvSel.setText("Y was " + String.valueOf(y));
            } else if (y < -0.24f ) {
                tvDir.setText("West tilt");
                tvSel.setText("Y was " + String.valueOf(y));
            }


        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        /*
         * When the app is brought to the foreground - using app on screen
         */
        protected void onResume() {
            super.onResume();
            // turn on the sensor
            mSensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        /*
         * App running but not on screen - in the background
         */
        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);    // turn off listener to save power    }
        }

    }
