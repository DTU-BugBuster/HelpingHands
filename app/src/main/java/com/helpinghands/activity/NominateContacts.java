package com.helpinghands.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.helpinghands.control.DatabaseHandler;
import com.helpinghands.entity.Contact;

public class NominateContacts extends Activity {

	//private Contact[] myContacts;
	//private int noOfContacts=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nominate_contacts);
	}
		/*public static boolean isEnoughInfo(String name,String phone,String email){
			if(name.equals("") || name.isEmpty())
				return false;
			else if((phone.equals("") || phone.isEmpty()) && (email.equals("") || email.isEmpty()))
				return false;
			else 
				return true;
		}*/

	    //@Override
	    public void afterNominateButton(View v) {

	        int id=0;
	        String name,phone,email;
	        
	        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        
	        name=((EditText)findViewById(R.id.name1)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone1)).getText().toString();
	        email=((EditText)findViewById(R.id.email1)).getText().toString();
	        
	       // if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	    
	        
	        name=((EditText)findViewById(R.id.name2)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone2)).getText().toString();
	        email=((EditText)findViewById(R.id.email2)).getText().toString();
	        
	       // if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	        
	        
	        
	        
	        
	        name=((EditText)findViewById(R.id.name3)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone3)).getText().toString();
	        email=((EditText)findViewById(R.id.email3)).getText().toString();
	        
	        //if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	        
	        
	        
	        
	        
	        name=((EditText)findViewById(R.id.name4)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone4)).getText().toString();
	        email=((EditText)findViewById(R.id.email4)).getText().toString();
	        
	        //if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	        
	        
	        
	        
	        name=((EditText)findViewById(R.id.name5)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone5)).getText().toString();
	        email=((EditText)findViewById(R.id.email5)).getText().toString();
	        
	       // if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	        
	        
	        name=((EditText)findViewById(R.id.name6)).getText().toString();
	        phone=((EditText)findViewById(R.id.phone6)).getText().toString();
	        email=((EditText)findViewById(R.id.email6)).getText().toString();
	        
	       // if(NominateContacts.isEnoughInfo(name, phone, email))
	        db.addContact(new Contact(++id,name,phone,email));
	        
	        
	        Toast.makeText(getApplicationContext(), "Contacts Saved In Database", Toast.LENGTH_SHORT).show();
	        
	        Intent intent = new Intent(this, SetShakeLevel.class);
	   		
	   		startActivity(intent);
	        
	        	        
	    }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nominate_contacts, menu);
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

	/*public Contact[] getMyContacts() {
		return myContacts;
	}*/

	
	
	
	
	
	
	
	/*public void setMyContacts(Contact[] myContacts) {
		this.myContacts = myContacts;
	}*/
}
