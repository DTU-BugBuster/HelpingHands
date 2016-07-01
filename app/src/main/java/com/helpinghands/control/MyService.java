package com.helpinghands.control;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.helpinghands.activity.Stop;
import com.helpinghands.entity.AccelerometerListener;

public class MyService extends Service implements AccelerometerListener {

	
	//public static final String DEVICE_SHAKEN="com.example.los.DEVICE_SHAKEN";
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		
		if (AccelerometerManager.isSupported(this)) {
            
            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
            Toast.makeText(this, "Listener Service Started", Toast.LENGTH_LONG).show();
        }
		
		return START_STICKY;
		}
		
	
	/*@Override
	public void onDestroy() {
		super.onDestroy();

		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
		}*/
	
	
	public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
         
    }
 
    public void onShake(float force) {
         
                 
        // Called when Motion Detected
        Toast.makeText(getBaseContext(), "Motion detected", 
                Toast.LENGTH_SHORT).show();
        
        
        
        Intent i = new Intent(this, Stop.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
                 
    }
 
  /*  @Override
    public void onResume() {
            super.onResume();
            Toast.makeText(getBaseContext(), "onResume Accelerometer Started", 
                    Toast.LENGTH_SHORT).show();
             
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isSupported(this)) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.startListening(this);
            }
    }
     
    @Override
    public void onStop() {
            super.onStop();
             
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isListening()) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.stopListening();
                 
                Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", 
                         Toast.LENGTH_SHORT).show();
            }
            
    }*/
     
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  distroy");
        Toast.makeText(getBaseContext(), "Service Destroyed",Toast.LENGTH_SHORT);
         
        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {
             
            //Start Accelerometer Listening
            AccelerometerManager.stopListening();
             
            Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped", 
                   Toast.LENGTH_SHORT).show();
        }
             
    }
 
	
	
	
	
	
	
	
	
	
		}



