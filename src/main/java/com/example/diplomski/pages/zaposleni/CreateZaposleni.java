package com.example.diplomski.pages.zaposleni;

import org.apache.tapestry5.annotations.InjectPage;
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

public class CreateZaposleni {
	
	
	@SessionState(create=false)
	private Zaposleni user;
	
	@Property
	private Zaposleni zaposleni;

	@InjectPage
	private Index index;
	
    @InjectPage
    private ShowZaposleni showZaposleni;

    @CommitAfter
    Object onSuccess()
    {
    	zaposleni.setTip(2);
    	DbBroker.getInstance().saveEntity(zaposleni);
    	
        return showZaposleni;
    }
    
    Object onActivate()
	{
		if(user.getIme() == null)
			return index;
		return null;
	}

}
