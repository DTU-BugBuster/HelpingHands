package com.helpinghands.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.helpinghands.control.AccelerometerManager;
import com.helpinghands.control.MyService;
import com.helpinghands.control.UserInfoManager;

public class SetShakeLevel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_shake_level);
	}
	
	public void onDefThreshClick(View v){
		UserInfoManager myInfo=new UserInfoManager(this);
		myInfo.saveAcceleration((float)AccelerometerManager.defThresh);
		myInfo.saveRegistered();
	}
	
	public void finishButton(View v){
		
		startService(new Intent(getBaseContext(), MyService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_shake_level, menu);
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
