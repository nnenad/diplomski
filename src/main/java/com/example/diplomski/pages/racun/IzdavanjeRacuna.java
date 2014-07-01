package com.example.diplomski.pages.racun;


import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Racun;
import com.example.diplomski.pages.poslovnipartner.PregledPoslovnihPartnera;

public class IzdavanjeRacuna {
	
	@Persist
	private Racun racun;
	
	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	private Object onActionFromPotvrdiRacun(){
		DbBroker.getInstance().saveEntity(racun);
		return PregledPoslovnihPartnera.class;
	}

}
