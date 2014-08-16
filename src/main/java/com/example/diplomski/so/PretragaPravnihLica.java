package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.PravnoLice;

public class PretragaPravnihLica extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		PravnoLice pravnoLiceZaPretragu = (PravnoLice) odo;
		return DbBroker.getInstance().gettAllPravnoLiceByName(pravnoLiceZaPretragu.getNaziv());
	}

}
