package sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import views.CanvasView;

/**
 * Class to help determine if a shake occurred
 */
public class SensorHelper implements SensorEventListener {


    private final SensorManager sensorManager;
    private final Sensor shakeDetector;
    private float prevValue;
    private float currentValue;
    private double accelerationForce;
    private CanvasView canvas;


    public SensorHelper(final Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerationForce = 10f;
        currentValue = SensorManager.GRAVITY_EARTH;
        prevValue = SensorManager.GRAVITY_EARTH;
        shakeDetector = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    public void onResume() {
        sensorManager.registerListener(this, shakeDetector, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            prevValue = currentValue;

            currentValue = (float) Math.sqrt((x * x + y * y + z * z));
            float delta = currentValue - prevValue;
            accelerationForce = accelerationForce * 0.9f + delta;

            if (accelerationForce > 12 || accelerationForce < -3) {
                canvas.erase();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // DOES NOTHING
    }

    public void setView(final CanvasView canvasView) {
        this.canvas = canvasView;
    }
}