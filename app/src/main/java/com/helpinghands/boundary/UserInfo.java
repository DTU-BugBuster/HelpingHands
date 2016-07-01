package com.helpinghands.boundary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.helpinghands.control.SendMailManager;
import com.helpinghands.control.UserInfoManager;

public class UserInfo extends Activity {

	private static final String HHid = "noreplyhelpinghands@gmail.com";
	private static final String HHpass = "hhx2015+";
	private static final String subject = "HelpingHands Verification Code";
	private static final String body = "Your Email Verification Code is : ";
	private static final String noReply = "This is a system generated email. Please do not reply back."
											+"\n"+"Thank You,\n"+"Helping Hands";
	private static String CODE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		CODE=""+(int)(Math.random()*10)+""+(int)(Math.random()*10)+
				""+(int)(Math.random()*10)+""+(int)(Math.random()*10);
		
	}

	public void afterUserInfoButton(View v){
		
		String inputCode=((EditText)findViewById(R.id.code)).getText().toString();
		if(inputCode.equals(CODE)){
			String name=((EditText)findViewById(R.id.name)).getText().toString();
			String email=((EditText)findViewById(R.id.email)).getText().toString();
			(new UserInfoManager(this)).saveUserInfo(name, email);
			Intent i = new Intent(this, NominateContacts.class);
	   		startActivity(i);	
		}
		else{
			((EditText)findViewById(R.id.code)).setText("");
			((EditText)findViewById(R.id.code)).requestFocus();
		}
			
		
	}
	
	public void sendCode(View v){
		// {senderID,senderPass,subject,body,recipientID}
		
		String recipientID=((EditText)findViewById(R.id.email)).getText().toString();
		String mailBody="Dear "+((EditText)findViewById(R.id.name)).getText().toString()
						+",\n"+body+CODE+"\n"+noReply;
		new SendMailManager().execute(HHid,HHpass,subject,mailBody,recipientID);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_info, menu);
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
