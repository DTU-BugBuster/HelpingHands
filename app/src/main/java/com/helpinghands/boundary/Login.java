package com.helpinghands.boundary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.helpinghands.control.UserInfoManager;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i;
		UserInfoManager myInfo=new UserInfoManager(this);
		if(myInfo.isRegistered()==false){
			i=new Intent(this,RegisterPIN.class);
			startActivity(i);
		}
		else{
			setContentView(R.layout.activity_login);	
		}
		
	}
	
	public static boolean isValidPIN(String pin){
		if(pin.equals(""))
			return false;
		else if(pin.length()!=4)
			return false;
		else
			return true;
	}

	public void onLoginClick(View v){
		UserInfoManager myInfo=new UserInfoManager(this);
		String enteredpin=((EditText)findViewById(R.id.loginpin)).getText().toString();
		String pin=""+myInfo.retreivePIN();
		if(isValidPIN(enteredpin) && enteredpin.equals(pin)){
			Intent i=new Intent(this,Alert.class);
			startActivity(i);
		}
		else{
			((EditText)findViewById(R.id.loginpin)).setText("");
			((EditText)findViewById(R.id.loginpin)).requestFocus();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
