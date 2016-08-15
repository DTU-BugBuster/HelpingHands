package com.helpinghands.utils;

import android.util.Log;

/**
 * Created by aloy on 8/13/2016.
 */
public class Logger {

    private static final boolean DEBUG=true;

    private Logger(){
        //  Does nothing.
        //  Prevents accidental init of class.
    }

    public static void v(String TAG,String message){
        if(DEBUG){
            Log.v(TAG,message);
        }
    }

    public static void d(String TAG,String message){
        if(DEBUG){
            Log.d(TAG,message);
        }
    }


    public static void i(String TAG,String message){
        if(DEBUG){
            Log.i(TAG,message);
        }
    }

    public static void w(String TAG,String message){
        if(DEBUG){
            Log.d(TAG,message);
        }
    }

    public static void e(String TAG,String message){
        if(DEBUG){
            Log.d(TAG,message);
        }
    }

}
