package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;

public class SacuvajRacun extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		return DbBroker.getInstance().saveEntity(odo);
	}

}
