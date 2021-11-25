import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{   
    private MyGLSurfaceView view;
    private SensorManager sensorManager;
    private Sensor sensor;
    private View counterView;
    private MyRenderer renderer;
    private TextView counter;

    private MediaPlayer mediaPlayerPilka;
    private MediaPlayer mediaPlayerRadosc;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        setContentView(R.layout.activity_main);

        view = (GLSurfaceView) findViewById(R.id.opengl);
        renderer = new MyRenderer();
        view.setRenderer(renderer);

        counterView = findViewById(R.id.textView);
        counter = (TextView)findViewById(R.id.textView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mediaPlayerPilka = MediaPlayer.create(this, R.raw.pilka);
        mediaPlayerRadosc = MediaPlayer.create(this, R.raw.radosc);
    }

     @Override
    public void onSensorChanged(SensorEvent event) {
       if(Globals.playPilka){
            mediaPlayerPilka.start();
            Globals.playPilka = false;
        }
        if(Globals.counter % 10 == 0 && Globals.counter != 0){
            mediaPlayerRadosc.start();
        }
        renderer.updateXOffset(event.values[1] * 0.01f);
        counter.setText(Integer.toString(Globals.counter));
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