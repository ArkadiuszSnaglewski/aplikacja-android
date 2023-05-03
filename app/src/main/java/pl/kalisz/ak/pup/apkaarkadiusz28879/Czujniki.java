package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Czujniki extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private TextView akcelerometr;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czujniki);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView czujniki = (TextView) findViewById(R.id.sensors);
        akcelerometr = (TextView) findViewById(R.id.akcelerometr);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder builder = new StringBuilder();
        builder.append("Wykaz czujników" + "\n");
        for(Sensor sensor : sensorList) {
            builder.append(sensor.getName() + "\n");
        }
        czujniki.setText(builder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder info = new StringBuilder();
        info.append("Wskazania akcelerometru: \n");
        for(int i=0; i<event.values.length; i++) {
            info.append("   [");
            if(i==0) {info.append("x");}
            if(i==1) {info.append("y");}
            if(i==2) {info.append("z");}
            info.append("] = ");
            info.append(event.values[i]);
            info.append("\n");
        }
        akcelerometr.setText(info);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
