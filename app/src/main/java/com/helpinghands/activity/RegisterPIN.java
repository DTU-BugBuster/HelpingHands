package com.helpinghands.activity;

import com.helpinghands.control.UserInfoManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPIN extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_pin);
	}
	
	public static boolean isValidPIN(String pin){
		if(pin.equals(""))
			return false;
		else if(pin.length()!=4)
			return false;
		else
			return true;
	}
	
	public void afterPINButton(View v){
		String pin1=((EditText)findViewById(R.id.pin1)).getText().toString();
   	   String pin2=((EditText)findViewById(R.id.pin2)).getText().toString();
   	   if(!isValidPIN(pin1) || !isValidPIN(pin2) || (pin1.equalsIgnoreCase(pin2)==false)){
   		   ((EditText)findViewById(R.id.pin1)).setText("");
   		   ((EditText)findViewById(R.id.pin2)).setText("");
   		   ((EditText)findViewById(R.id.pin1)).requestFocus();
   		Toast.makeText(this, "PINs don't match/not 4 digits", Toast.LENGTH_SHORT).show();
             
         }
   	   else{
   		   new UserInfoManager(this).savePIN(Integer.parseInt(pin1));
   		Intent intent = new Intent(this, UserInfo.class);
   		
   		startActivity(intent);
   	   }
   		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_pin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
