package com.example.diplomski.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SessionState;

import com.example.diplomski.entities.Zaposleni;

public class About
{
	@SessionState(create=false)
	private Zaposleni myUser;
	
	
	
	public Zaposleni getMyUser() {
		return myUser;
	}

	public void setMyUser(Zaposleni myUser) {
		this.myUser = myUser;
	}

	private String passedValue;

	public String getPassedValue() {
		return passedValue;
	}

	public void setPassedValue(String passedValue) {
		this.passedValue = passedValue;
	}
	
	void onActivate(String message){
		System.out.println("Another page is activated! The message is: "+message);
		this.passedValue = message;
		
	}
	
	String onPassivate(){
		System.out.println("Another page is passivate...");
		
		return passedValue;
	}
	
}
