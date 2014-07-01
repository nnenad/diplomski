package com.example.diplomski.pages;

import org.apache.tapestry5.annotations.OnEvent;

public class PoslovniPartneri {
	
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		System.out.println("Klikno si na: "+ type);
		this.type = type;
	}
	
	private String userName = "koja";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@OnEvent(value="onchange", component="selectLeft")
	 Object printValue(Object event)
    {
		System.out.println("Klikno si na: "+ event.toString());
        return null;
    }
	
	@OnEvent(value="onclick", component="selectLeft")
	 Object printValue1(Object event)
   {
		System.out.println("Klikno si na: "+ event.toString());
       return null;
   }
	
	@OnEvent(value="onchange", component="aovo")
	 Object printValue2(Object event)
   {
		System.out.println("Klikno si na: "+ event.toString());
       return null;
   }
	
	@OnEvent(value="onclick", component="aovo")
	 Object printValue3(Object event)
  {
		System.out.println("Klikno si na: "+ event.toString());
      return null;
  }
}
