package com.example.diplomski.pages.zaposleni;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.Index;

public class CreateZaposleni {
	
	@Property
	private Zaposleni zaposleni;


    @InjectPage
    private Index index;

    @CommitAfter
    Object onSuccess()
    {
    	
    	 SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
         //SessionFactory sf = new Configuration().configure().buildSessionFactory();
         Session session = sf.openSession();
        session.beginTransaction();
        session.persist(zaposleni);

        session.getTransaction().commit();
        return index;
    }

}
