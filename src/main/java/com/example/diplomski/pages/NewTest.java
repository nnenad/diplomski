package com.example.diplomski.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.*;
import org.apache.tapestry5.corelib.components.DateField;
public class NewTest {

	
	private String message = "tezt field type in";
	
	@Property
	private Date enterDate;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		System.out.println(message);
		this.message = message;
	}
	
	
}
