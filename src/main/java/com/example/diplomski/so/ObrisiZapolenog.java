package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class ObrisiZapolenog extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		Zaposleni zaposleni = (Zaposleni) odo;
		return DbBroker.getInstance().obrisiZapolenog(zaposleni);
	}

}
