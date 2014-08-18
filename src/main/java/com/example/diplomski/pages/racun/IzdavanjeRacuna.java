package com.example.diplomski.pages.racun;


import org.apache.tapestry5.Asset;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Proizvod;
import com.example.diplomski.entities.Racun;
import com.example.diplomski.entities.StavkaRacuna;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.poslovnipartner.PregledPoslovnihPartnera;
import com.example.diplomski.pomocne_operacije.SelectProizvodUsluga;
import com.example.diplomski.so.PromeniStanjeProizvodaUsluge;
import com.example.diplomski.so.SacuvajRacun;

@Import(stylesheet="context:css/racuntable.css")
public class IzdavanjeRacuna {
	
	@Persist
	private Racun racun;
	
	@SessionState
	@Property
	private Zaposleni zaposleni;
	
	private StavkaRacuna tekucaStavka;

	public StavkaRacuna getTekucaStavka() {
		return tekucaStavka;
	}

	public void setTekucaStavka(StavkaRacuna tekucaStavka) {
		this.tekucaStavka = tekucaStavka;
	}

	public Racun getRacun() {
		if(racun == null){
			return new Racun();
		}
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	@OnEvent(value=EventConstants.ACTION)
	Object onActionFrompotvrdiRacun() throws Exception{
		SacuvajRacun sacuvajRacun = new SacuvajRacun();
		sacuvajRacun.izvrsiSO(racun);
		
		return PregledPoslovnihPartnera.class;
	}
	
	Object onActionFromotkaziRacun() throws Exception{
		
		for(StavkaRacuna stavka : zaposleni.getCurrent().getStavkaRacunas()){
   		 if(stavka.getProizvodUsluga() instanceof Proizvod){
   			 
   			 Proizvod prSel = (Proizvod) stavka.getProizvodUsluga();
   			 SelectProizvodUsluga proizvodUslugaSO = new SelectProizvodUsluga();
   			 
   			 Proizvod proizvodSelektuj = (Proizvod) proizvodUslugaSO.izvrsiSO(prSel);
   		 
   			
   			 PromeniStanjeProizvodaUsluge promeniStanjeProizvoda = new PromeniStanjeProizvodaUsluge();

   			 prSel.setKolicina(proizvodSelektuj.getKolicina() + stavka.getKolicina()+1);
   			 
   			 promeniStanjeProizvoda.izvrsiSO(prSel);
   			 
   		 }
   	}
   	
       zaposleni.setCurrent(null);


       return PregledPoslovnihPartnera.class;
	}

}
