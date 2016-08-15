package com.helpinghands.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helpinghands.R;
import com.helpinghands.control.DatabaseHandler;
import com.helpinghands.control.GetLocationManager;
import com.helpinghands.control.TriggerNotifManager;
import com.helpinghands.control.UserInfoManager;
import com.helpinghands.entity.Contact;

import java.util.List;

public class Stop extends Activity {

	TriggerNotifManager myNotif;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stop);
		
		
		DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contactList=db.getAllContacts();
        GetLocationManager myLoc=new GetLocationManager(this);
        String name=(new UserInfoManager(this)).retreiveUserName();
        
        myNotif=new TriggerNotifManager(name, contactList, myLoc);
        myNotif.sendDangerSMS();
        myNotif.sendDangerMail();
        
	}
	
	public static boolean isValidPIN(String pin){
		if(pin.equals(""))
			return false;
		else if(pin.length()!=4)
			return false;
		else
			return true;
	}
	
	public void onSTOPClick(View v){
		
		String PIN=""+(new UserInfoManager(this)).retreivePIN();
		String enteredPIN=((EditText)findViewById(R.id.stoppin)).getText().toString();
		
		if(isValidPIN(enteredPIN) && enteredPIN.equals(PIN))
		myNotif.sendSafeSMS();
		else{
			((EditText)findViewById(R.id.stoppin)).setText("");
			((EditText)findViewById(R.id.stoppin)).requestFocus();
			Toast.makeText(this, "Entered Wrong PIN", Toast.LENGTH_SHORT).show();
		}
			
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
