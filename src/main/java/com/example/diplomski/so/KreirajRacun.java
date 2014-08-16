package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;

public class KreirajRacun extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		DbBroker.getInstance().saveEntity(odo);
		return null;
	}

}
