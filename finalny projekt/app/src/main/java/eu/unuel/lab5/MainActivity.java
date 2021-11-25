package eu.unuel.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor sensor;
    private GLSurfaceView view;
    private View counterView;
    private MyRenderer renderer;
    private TextView counter;

    private MediaPlayer mediaPlayerPilka;
    private MediaPlayer mediaPlayerRadosc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //na 3.5
        //view = MyGLSurfaceView(getApplicationContext());


        //na 4
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        /// na 4.5
        setContentView(R.layout.activity_main);
        view = (GLSurfaceView) findViewById(R.id.opengl);
        renderer = new MyRenderer();
        view.setRenderer(renderer);
        counterView = findViewById(R.id.textView);
        counter = (TextView)findViewById(R.id.textView);


        //setContentView(view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mediaPlayerPilka = MediaPlayer.create(this, R.raw.pilka);
        mediaPlayerRadosc = MediaPlayer.create(this, R.raw.radosc);
    }
    ///na 4
    @Override
    public void onSensorChanged(SensorEvent event) {
       //view.updateXOffset(event.values[1] * 0.01f);
        //na 4.5
        if(Globals.playPilka){
            mediaPlayerPilka.start();
            Globals.playPilka = false;
        }
        if(Globals.counter % 10 == 0 && Globals.counter != 0){
            mediaPlayerRadosc.start();
        }
        renderer.updateXOffset(event.values[1] * 0.01f);
        counter.setText(Integer.toString(Globals.counter));

        //
        //System.out.println("X: " + event.values[0] + " Y: " + event.values[1] + " X: " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}