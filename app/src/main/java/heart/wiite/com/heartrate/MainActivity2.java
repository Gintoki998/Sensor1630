package heart.wiite.com.heartrate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity2 extends BaseActivity {
	TextView mHeartTextView;
	SensorManager mSensorManager;
	Sensor mStepCount;
	int mCount = 0;



	@Override
	protected void init() {
		setContentView(R.layout.activity_main2);
		mHeartTextView = (TextView) this.findViewById(R.id.tvtitle);
		mSensorManager = ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
	}

	@Override
	protected void setSensorHelp() {
		mStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
		mSensorManager.registerListener(sensorLisener, mStepCount, 0);
	}

	private SensorEventListener sensorLisener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor paramAnonymousSensor,
				int paramAnonymousInt) {

		}

		public void onSensorChanged(SensorEvent paramAnonymousSensorEvent) {
			mCount = (int) paramAnonymousSensorEvent.values[0];
			mHeartTextView.setText("步数："+mCount);
			
		}
	};

	protected void unRegisterSensor() {
		if (mSensorManager != null) {
			mSensorManager.unregisterListener(sensorLisener);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unRegisterSensor();

	}
	
	
}
