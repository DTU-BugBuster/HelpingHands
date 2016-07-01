package com.helpinghands.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserInfoManager {
    // Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "HHData";
     
    private static final String KEY_REGISTERED = "registered";

	private static final String KEY_USERNAME = "name";

	private static final String KEY_EMAIL = "email";
     
	private static final String KEY_ACCELERATION = "acceleration";

	private static final String KEY_PIN = "pin"; 
   
		
			
		// Constructor
	   public UserInfoManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        
    }
    
    public void saveRegistered(){
    	editor = pref.edit();
    	// Storing login value as TRUE
        editor.putBoolean(KEY_REGISTERED, true);
        editor.commit();
        
    }
    
    public void savePIN(int pin){
    	editor = pref.edit();
    	// Storing login value as TRUE
        editor.putInt(KEY_PIN,pin);
        editor.commit();
        
    }
    
    
    public void saveAcceleration(float acceleration){
    	editor = pref.edit();
    	editor.putFloat(KEY_ACCELERATION, acceleration);
        editor.commit();
    }
    
    public void saveUserInfo(String name,String email){
    	editor = pref.edit();
    	editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_EMAIL, name);
        editor.commit();
        
    }
    
    public boolean isRegistered(){
        return pref.getBoolean(KEY_REGISTERED, false);
    }
    
    public int retreivePIN(){
    	return pref.getInt(KEY_PIN, -1);
    }
    public String retreiveUserName(){
    	
    	return pref.getString(KEY_USERNAME, null);
    }
    
    public String retreiveEmail(){
    	
    	return pref.getString(KEY_EMAIL, null);
    }
    
    public float retreiveAcceleration(){
    	
    	return pref.getFloat(KEY_ACCELERATION,0.0f);
    }
    
}