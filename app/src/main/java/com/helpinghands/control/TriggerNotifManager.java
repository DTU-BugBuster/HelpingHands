package com.helpinghands.control;

import com.helpinghands.entity.Contact;

import java.util.List;

public class TriggerNotifManager {

	private static final String MESSGAE_BODY = "in danger.Location is : ";;
	private static final String HHid = "noreplyhelpinghands@gmail.com";
	private static final String HHpass = "hhx2015+";
	private static final String subject = "I am in Danger";
	private static final String noReply = "This is a system generated email. Please Contact the person in danger."
											+"\n"+"Thank You,\n"+"Helping Hands";
	
	List<Contact> contactList;
	GetLocationManager myLoc;
	SendSMSManager mySMS;
	SendMailManager myEmail;
	String name,latlong,address;
	
	public TriggerNotifManager(String name,List<Contact> contactList,GetLocationManager myLoc){
		this.contactList=contactList;
		this.myLoc=myLoc;
		mySMS=new SendSMSManager();
		myEmail=new SendMailManager();
		
		latlong=myLoc.getLatLong();
        address=myLoc.getCompleteAddress();
        this.name=name;
	}
	
	public void sendDangerSMS(){
			
		for(int i=0;i<contactList.size();i++){
        	
        	String phone=(contactList.get(i)).getPhoneNumber();
        	mySMS.sendSMSMessage(phone,name+" : HELP !! "+"I am "+MESSGAE_BODY+latlong+"\n"+address);
        	
        	
        }
	}
	
	public void sendDangerMail(){
		
		for(int i=0;i<contactList.size();i++){
        	
        String recipient=(contactList.get(i)).getEmail();
		// {senderID,senderPass,subject,body,recipientID}
    	myEmail.execute(HHid,HHpass,subject,"HELP !! "+name+" is "+MESSGAE_BODY+latlong+"\n"+address+"\n"+noReply,recipient);
			}
	}
	
	public void sendSafeSMS(){
		
		for(int i=0;i<contactList.size();i++){
        	
        	String phone=(contactList.get(i)).getPhoneNumber();
        	mySMS.sendSMSMessage(phone,name+" : I am Safe ");
        	
        	
        }
	}
	
	
	
}
