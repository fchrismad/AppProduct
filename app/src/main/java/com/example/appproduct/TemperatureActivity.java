package com.example.appproduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView, textView2;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private boolean tempAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            tempAvailable = true;
        }else {
            textView.setText(R.string.pesan_gagal);
            tempAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0] + "°C");
        if (event.values[0] >= 20){
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage(R.string.alert);
            dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            textView2.setText("Tolong Simpan Pada Lemari Es Bersuhu ±5°C, Agar Bertahan Lama");
        }else {
            textView2.setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tempAvailable){
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tempAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}