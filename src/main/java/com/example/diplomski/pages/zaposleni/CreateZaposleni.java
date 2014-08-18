package com.example.diplomski.pages.zaposleni;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.Index;
import com.example.diplomski.so.DodavanjeZaposlenog;

public class CreateZaposleni {
	
	
	@SessionState(create=false)
	private Zaposleni user;
	
	@Property
	private Zaposleni zaposleni;

	@InjectPage
	private Index index;
	
    @InjectPage
    private ShowZaposleni showZaposleni;
    
    @Persist(PersistenceConstants.FLASH)
	private String messageText;
    
    @Persist(PersistenceConstants.FLASH)
    private String messageColor;
    
    
	
	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

    @CommitAfter
    Object onSuccess() throws Exception
    {
    	zaposleni.setTip(2);
    	
    	DodavanjeZaposlenog dodavanjeZaposlenog = new DodavanjeZaposlenog();
    	zaposleni = (Zaposleni) dodavanjeZaposlenog.izvrsiSO(zaposleni);
    	
    	if(zaposleni.getIdZaposlenog() == -1){
    		messageText = "Zaposleni sa tim korisnickim imenom vec postoji!";
   		 	messageColor = "red";
    		
    	}else{
    		 messageText = "Zaposleni je uspesno sacuvan";
    		 messageColor = "green";
    		
    	}
    	
        return this;
    }
    
    Object onActivate()
	{
		if(user.getIme() == null)
			return index;
		return null;
	}

}
