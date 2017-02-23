package com.helpinghands.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.helpinghands.constants.AppConstant;

/**
 * Created by aloy on 8/13/2016.
 */
public class SharedPrefUtils {

    private SharedPreferences sharedPreferences;
    private static final String TAG=SharedPrefUtils.class.getSimpleName();

    public void initSharedPref(Context context){

        if(sharedPreferences==null && context!=null){
            Logger.i(TAG," SP Initialized");
            sharedPreferences=context.getApplicationContext().getSharedPreferences(AppConstant.SHARED_PREF_FILE, Context.MODE_PRIVATE);
        }
    }



    public void saveValue(Context context,String key,String value){

        if(sharedPreferences==null){
            initSharedPref(context);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
        Logger.i(TAG,"saved value "+key+" -> "+value);


    }

    public String getValue(Context context,String key){

        String value=null;
        if(sharedPreferences==null){
            initSharedPref(context);
        }

        value=sharedPreferences.getString(key,null);
        Logger.i(TAG,"retrieved value "+key+" -> "+value);

        return value;

    }
}
