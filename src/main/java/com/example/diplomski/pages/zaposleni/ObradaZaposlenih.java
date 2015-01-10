package com.example.diplomski.pages.zaposleni;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.so.ObrisiZapolenog;
import com.example.diplomski.so.PronadjiKorisnika;

public class ObradaZaposlenih {
	
	@Property
	private String prezime;
	
	@InjectComponent
	private Zone prikazZaposlenih;
	
	@InjectComponent
	private Zone poruka;
	
	@Property
	private Zaposleni zaposleni;
	
	@Property
	@Persist
	private List<Zaposleni> listaZaposlenih;
	
	@Property
	private String messageColor;
	
	@Property
	private String messageText;
	
	 @OnEvent(value= "submit" , component="searchForm")
	 public Object kreirajRacun() throws Exception{
		 PronadjiKorisnika pk = new PronadjiKorisnika();
		 Zaposleni z = new Zaposleni();
		 z.setPrezime(prezime);
		listaZaposlenih = (List<Zaposleni>) pk.izvrsiSO(z);
		 return prikazZaposlenih.getBody();
	 }
	 
	 Object onActionFromObrisiZap(Integer zaposleniId) throws Exception{
		 ObrisiZapolenog oz = new ObrisiZapolenog();
		 Zaposleni zap = new Zaposleni();
		 zap.setIdZaposlenog(zaposleniId);
		 Zaposleni zapFromSO = (Zaposleni) oz.izvrsiSO(zap);
		 if(zapFromSO.getKorisnickoIme() != null){
			 messageColor = "green";
			 messageText = "Zaposleni "+zapFromSO.getIme()+" "+zapFromSO.getPrezime()+" je uspesno isbrisan!";
		 }else
		 {
			 messageColor = "red";
			 messageText = "Doslo je do greske pri brisanju"; 
		 }
		 return poruka.getBody();
	}
}
