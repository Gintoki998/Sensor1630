package heart.wiite.com.heartrate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity3
        extends BaseActivity implements SensorEventListener {
    TextView mHeartTextView;

    private SensorManager sensorManager2;
    Sensor sensor2;


    @Override
    protected void init() {
        setContentView(R.layout.activity_main3);
        mHeartTextView = (TextView) this.findViewById(R.id.tvtitle);
        sensorManager2 = (SensorManager) this.getSystemService(SENSOR_SERVICE);
    }


    @Override
    protected void setSensorHelp() {
        sensor2 = sensorManager2.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        sensorManager2.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterSensor();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        sensorManager2.registerListener(this, sensor2,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        unRegisterSensor();
    }

    protected void unRegisterSensor() {
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_BEAT) {
            int oxygen = (int) event.values[1];
            if (oxygen > 0) {
                mHeartTextView.setText(" 血氧：" + oxygen);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
