package com.example.diplomski.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import com.example.diplomski.entities.Racun;
import com.example.diplomski.entities.Zaposleni;

public class About
{
	@SessionState(create=false)
	private Zaposleni myUser;
	
	@Persist
	private Racun racun;

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

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
