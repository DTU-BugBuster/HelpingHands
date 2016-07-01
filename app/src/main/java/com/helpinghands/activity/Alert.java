package com.helpinghands.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.helpinghands.control.DatabaseHandler;
import com.helpinghands.control.GetLocationManager;
import com.helpinghands.control.TriggerNotifManager;
import com.helpinghands.control.UserInfoManager;
import com.helpinghands.entity.Contact;

public class Alert extends Activity {

	
	TriggerNotifManager myNotif;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert);
			
		DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contactList=db.getAllContacts();
        GetLocationManager myLoc=new GetLocationManager(this);
        String name=(new UserInfoManager(this)).retreiveUserName();
        myNotif=new TriggerNotifManager(name, contactList, myLoc);
		
	}
	
	public void onALERTClick(View v){
	        
        myNotif.sendDangerSMS();
        myNotif.sendDangerMail();
       			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stop, menu);
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
