package com.helpinghands.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.api.client.googleapis.util.Utils;
import com.helpinghands.constants.AppConstant;
import com.helpinghands.utils.Logger;
import com.helpinghands.utils.SharedPrefUtils;
import com.sun.mail.imap.Utility;

public class ShakeListenerService extends Service implements SensorEventListener{


    private SensorManager sensorManager;
    private Sensor senAccelerometer;
    private Vibrator mVibrator;
    private SensorEventListener sensorEventListener;
    private int SHAKE_THRESHOLD = Integer.MAX_VALUE;
    private static final String TAG = ShakeListenerService.class.getSimpleName();

    public ShakeListenerService(){
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);


        SHAKE_THRESHOLD = Integer.MAX_VALUE;

        String thr = new SharedPrefUtils().getValue(this,AppConstant.USER_SHAKE_THRESHOLD);


        SHAKE_THRESHOLD = Integer.parseInt(thr);

        Toast.makeText(getApplicationContext(),"shake threshold read from service "+SHAKE_THRESHOLD,Toast.LENGTH_SHORT).show();



        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        if(sensorManager!=null){
            Logger.d(TAG,"***** REGISTERING SENSOR MANAGER ***** ");
            sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }






        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(sensorManager!=null){
            Logger.d(TAG,"***** UNREGISTERING SENSOR MANAGER ***** ");
            sensorManager.unregisterListener(this,senAccelerometer);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Logger.d(TAG,"**** onSensorChanged called");
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = 0;//event.values[1];
            float z = 0;//event.values[2];


            float acc = (float) Math.abs(Math.sqrt(x * x + y * y + z * z));

            Logger.d(TAG,"acceleration :"+acc);

            int acceleration = (int)acc;

            if(acceleration>=SHAKE_THRESHOLD){
                // trigger notifs
                // launch START/STOP Screen
                Toast.makeText(getApplicationContext(),"Help on your way!",Toast.LENGTH_SHORT).show();
                vibrateDevice();
            }


        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Intent recreateServiceIntent = new Intent(AppConstant.RECREATE_SHAKE_SERVICE_INTENT);
        sendBroadcast(recreateServiceIntent);

        super.onTaskRemoved(rootIntent);
    }

    private void vibrateDevice() {
        if(mVibrator!=null){
            mVibrator.vibrate(1500);

        }
    }
}
