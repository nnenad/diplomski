package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class PronadjiKorisnika extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		Zaposleni z = (Zaposleni) odo;
		return DbBroker.getInstance().pronadjiKorisnika(z);
	}

}
