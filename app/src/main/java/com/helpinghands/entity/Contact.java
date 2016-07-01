
package com.helpinghands.entity; 

public class Contact {
     
    //private variables
    private int id;
    private String name,phone_number,email;
     
   	// Empty constructor
    public Contact(){
         
    }
    // constructor
    public Contact(int id, String name, String phone_number,String email){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email=email;
    }
     
    // constructor
    public Contact(String name, String phone_number){
        this.name = name;
        this.phone_number = phone_number;
    }
    // getting ID
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
     
    // getting name
    public String getName(){
        return this.name;
    }
     
    // setting name
    public void setName(String name){
        this.name = name;
    }
     
    // getting phone number
    public String getPhoneNumber(){
        return this.phone_number;
    }
     
    // setting phone number
    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return name+" "+phone_number+" "+email;
	}
}