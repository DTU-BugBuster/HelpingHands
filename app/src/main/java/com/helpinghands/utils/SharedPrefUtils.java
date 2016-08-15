package com.helpinghands.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.helpinghands.constants.AppConstant;

/**
 * Created by aloy on 8/13/2016.
 */
public class SharedPrefUtils {

    private static SharedPreferences sharedPreferences;
    private static final String TAG=SharedPrefUtils.class.getSimpleName();

    public static void initSharedPref(Context context){

        if(sharedPreferences==null && context!=null){
            Logger.i(TAG," SP Initialized");
            sharedPreferences=context.getSharedPreferences(AppConstant.SHARED_PREF_FILE, Context.MODE_PRIVATE);
        }
    }

    private static SharedPreferences getSharedPref(){

        return sharedPreferences;
    }


    public static void saveValue(String key,String value){

        if(sharedPreferences!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key,value);
            editor.commit();

            Logger.i(TAG,"saved value "+key+" -> "+value);
        }

    }

    public static String getValue(String key){

        String value=null;
        if(sharedPreferences!=null){
             value=sharedPreferences.getString(key,null);

        }

        Logger.i(TAG,"retrieved value "+key+" -> "+value);

        return value;

    }
}
