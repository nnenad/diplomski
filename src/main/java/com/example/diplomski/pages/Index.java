package com.example.diplomski.pages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;


import com.example.diplomski.celebrity.model.User;
import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.so.ProveraKorisnika;
import com.google.common.collect.Sets.SetView;

/**
 * Start page of application diplomski.
 */
public class Index {

	private String userName;
	private String password;

	@SessionState
	private Zaposleni user;
	
	@Persist(PersistenceConstants.FLASH)
	private String hiddenMessage;
	
	
	public String getHiddenMessage() {
		if(hiddenMessage == null){
			hiddenMessage = "hidden";
		}
		return hiddenMessage;
	}

	public void setHiddenMessage(String hiddenMessage) {
		if(hiddenMessage == null){
			this.hiddenMessage = "hidden";
		}else{
			this.hiddenMessage = hiddenMessage;
		}
	}

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

	Object onSubmitFromLoginForm() throws Exception {
		Class nextPage = null;
		Zaposleni authenticatedUser = null;
		ProveraKorisnika ak = new ProveraKorisnika();
		Zaposleni zaposleniZaAutentikaciju = new Zaposleni();
		zaposleniZaAutentikaciju.setKorisnickoIme(userName);
		zaposleniZaAutentikaciju.setSifra(password);
		authenticatedUser = (Zaposleni) ak.izvrsiSO(zaposleniZaAutentikaciju);
		if (authenticatedUser != null) {
			user = authenticatedUser;
			nextPage = About.class;
		} else {
			setHiddenMessage("none");
			return this;
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
