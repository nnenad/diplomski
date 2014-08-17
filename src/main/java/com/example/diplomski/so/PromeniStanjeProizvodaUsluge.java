package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Proizvod;

public class PromeniStanjeProizvodaUsluge extends OpstaSO{

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		Proizvod smanjiProizvod = (Proizvod) odo;
		DbBroker.getInstance().smaniKolicinuProizvoda(smanjiProizvod);
		return null;
	}

}
