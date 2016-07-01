package com.helpinghands.control;

import android.os.AsyncTask;

import com.helpinghands.entity.GMailSender;

public class SendMailManager extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        /*for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }*/
    	
    	// {senderID,senderPass,subject,body,recipientID}
    	GMailSender sender = new GMailSender(params[0],params[1]);
        try {
			sender.sendMail(params[2],params[3],params[0],params[4]); 
        	
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
        
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
}

