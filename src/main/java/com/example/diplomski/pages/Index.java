package com.example.diplomski.pages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;


import com.example.diplomski.celebrity.model.User;
import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.util.Security;

/**
 * Start page of application diplomski.
 */
public class Index {

	private String userName;
	private String password;

	@SessionState
	private Zaposleni user;
	
	
	
	public Zaposleni getUser() {
		return user;
	}

	public void setUser(Zaposleni user) {
		this.user = user;
	}

	private String message = "initial value";
	
	
	@InjectPage
	private About about;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		System.out.println("Setting the message: " + message);
		this.message = message;
	}
	
//	@OnEvent(value="submit", component="userInputForm")
//	Object onFormSubmit(){
//		about.setPassedValue(message);
//		System.out.println("Handling form submission!");
//		user.setIme(message);
//		return about;
//	}

	Object onSubmitFromLoginForm() {
		Class nextPage = null;
		Zaposleni authenticatedUser = null;
		authenticatedUser = Security.authenticate(userName, password);
		if (authenticatedUser != null) {
			user = authenticatedUser;
			nextPage = About.class;
		} else {
			nextPage = Registration.class;
		}
		return nextPage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
