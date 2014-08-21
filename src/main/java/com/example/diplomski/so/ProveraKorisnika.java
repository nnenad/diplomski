package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class ProveraKorisnika extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		Zaposleni zaposleniZaAutentikaciju = (Zaposleni) odo;
		Zaposleni zaposleni = DbBroker.getInstance().getZaposleniByUserNameAndPass(zaposleniZaAutentikaciju.getKorisnickoIme(), zaposleniZaAutentikaciju.getSifra());
		if (zaposleni != null) {
			return zaposleni;
		}
		return null;
	}

}
