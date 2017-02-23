package com.helpinghands.fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.helpinghands.R;
import com.helpinghands.activity.HomeActivity;
import com.helpinghands.constants.AppConstant;
import com.helpinghands.service.ShakeListenerService;
import com.helpinghands.utils.Logger;
import com.helpinghands.utils.SharedPrefUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetShakeLevelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetShakeLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetShakeLevelFragment extends Fragment implements SensorEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private HomeActivity homeActivity;

    private SensorManager sensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 18;


    private TextView mAccleration;
    private Vibrator mVibrator;
    private Button mRecordingButton, mDoneButton;

    int lastAccelerationValue=0;
    int currentAccelerationValue=0;

    // acceleration value recorded before saving
    int recordedAccelerationValue = 0;

    boolean mIsRecording=false;

    private String recordingStage;
    private static final String TAG = SetShakeLevelFragment.class.getSimpleName();

    public SetShakeLevelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetShakeLevelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetShakeLevelFragment newInstance(String param1, String param2) {
        SetShakeLevelFragment fragment = new SetShakeLevelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        sensorManager = (SensorManager)homeActivity.getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mIsRecording = false;
        currentAccelerationValue= 0;
        lastAccelerationValue=0;
        //sensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        unregisterShakeListener();

        mVibrator = (Vibrator)homeActivity.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_set_shake_level, container, false);

        mAccleration=(TextView)view.findViewById(R.id.text_acceleration);
        mRecordingButton = (Button)view.findViewById(R.id.btn_record);
        mDoneButton = (Button)view.findViewById(R.id.btn_save_shake_level);

        mRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recordingStatus = (String) mRecordingButton.getText();

                if(recordingStatus.equalsIgnoreCase("Start Recording")){
                    mIsRecording = true;
                    mRecordingButton.setText("Recording");
                    currentAccelerationValue= 0;
                    lastAccelerationValue=0;
                    recordedAccelerationValue=0;
                    registerShakeListener();
                }
                else if(recordingStatus.equalsIgnoreCase("Recording")){

                    // Do nothing
                    // User pressed button second time without setting acceleration
                }
                else if(recordingStatus.equalsIgnoreCase("Retry")){
                    mIsRecording = true;
                    mRecordingButton.setText("Recording");
                    currentAccelerationValue= 0;
                    lastAccelerationValue=0;
                    recordedAccelerationValue = 0;
                    registerShakeListener();
                }



            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recordedAccelerationValue == 0){
                    Toast.makeText(homeActivity,"Please set shake threshold value first!",Toast.LENGTH_SHORT).show();
                }
                else{
                    new SharedPrefUtils().saveValue(homeActivity,AppConstant.USER_SHAKE_THRESHOLD,""+recordedAccelerationValue);

                    // start shake listener service in background
                    Intent shakeIntent = new Intent(homeActivity.getApplicationContext(), ShakeListenerService.class);
                    homeActivity.startService(shakeIntent);

                    homeActivity.pushFragment(new DoneFragment());

                }
            }
        });


        return view;
    }

    private void setRecordingButtonText() {

        if(mIsRecording){
            mRecordingButton.setText("Recording");
            registerShakeListener();
        }
        else{
            mRecordingButton.setText("Start Recording");
        }
    }

    private void registerShakeListener() {

        if(sensorManager!=null){
            sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    private void unregisterShakeListener() {

        if(sensorManager!=null){
            sensorManager.unregisterListener(this,senAccelerometer);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity=(HomeActivity)context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
       unregisterShakeListener();
        mIsRecording = false;

    }

    @Override
    public void onResume() {
        super.onResume();
        setRecordingButtonText();

       /* if(sensorManager!=null){
            sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        }*/

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        Sensor sensor=event.sensor;

        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = 0;//event.values[1];
            float z = 0;//event.values[2];


            if(mIsRecording){
                long curTime = System.currentTimeMillis();

                long diffTime = (curTime - lastUpdate);
                //lastUpdate = curTime;


                float acceleration = (float)Math.abs(Math.sqrt(x*x+y*y+z*z));

                currentAccelerationValue=(int)acceleration;

                /*if(curTime%2000==0){
                    Logger.d(TAG,"C acc : "+currentAccelerationValue+" L acc: "+lastAccelerationValue+" curTime: "+curTime);
                }*/


                if(currentAccelerationValue<lastAccelerationValue &&
                        lastAccelerationValue>SHAKE_THRESHOLD
                        ){

                    Logger.d(TAG,"C acc : "+currentAccelerationValue+" L acc: "+lastAccelerationValue);
                    lastUpdate = System.currentTimeMillis();
                    vibrateDevice();
                    recordedAccelerationValue = lastAccelerationValue;
                    updateView(lastAccelerationValue);

                }
                else{
                    lastAccelerationValue=currentAccelerationValue;
                }
            }


        }
    }

    private void updateView(int accelerationValue) {

        if(mAccleration!=null && mRecordingButton!=null){
            mAccleration.setText("Acceleration : "+accelerationValue+" m/s2");
            mRecordingButton.setText("Retry");
        }
    }

    private void vibrateDevice() {

        if(mVibrator!=null){
            mIsRecording=false;
            mVibrator.vibrate(1500);

        }
        unregisterShakeListener();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
