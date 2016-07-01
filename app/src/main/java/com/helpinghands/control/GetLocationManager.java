package com.helpinghands.control;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.helpinghands.entity.GPSTracker;

public class GetLocationManager {

	GPSTracker gps;
	double latitude;
	double longitude;
	Context context;
	
	public GetLocationManager(Context context){
		this.context=context;
	}
	
	public String getLatLong(){
 
	gps=new GPSTracker(context);
	if(gps.canGetLocation()){
	latitude = gps.getLatitude();
	longitude = gps.getLongitude();
	}
	else{
		gps.showSettingsAlert();
	}
	return "Latitude : "+latitude+" Longitude : "+longitude;
	}
	
	public String getCompleteAddress(){
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(context, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);
		
		return address+"\n"+city+"\n"+country;
	}
	
}
		