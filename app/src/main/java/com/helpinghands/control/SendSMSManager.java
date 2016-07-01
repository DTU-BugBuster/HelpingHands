package com.helpinghands.control;

import android.telephony.SmsManager;
import android.util.Log;

public class SendSMSManager {

	
	/*@Override
    protected String doInBackground(String... params) {

		String phoneNo=params[0];
		String message=params[1];
	      try {
	         SmsManager smsManager = SmsManager.getDefault();
	         smsManager.sendTextMessage(phoneNo, null, message, null, null);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return "Executed";
	}*/
	
	public void sendSMSMessage(String phoneNo,String message) {
	      Log.i("Send SMS", "");

	      

	      try {
	         SmsManager smsManager = SmsManager.getDefault();
	         smsManager.sendTextMessage(phoneNo, null, message, null, null);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	/*@Override
    protected void onPostExecute(String result) {
        
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}*/
}
