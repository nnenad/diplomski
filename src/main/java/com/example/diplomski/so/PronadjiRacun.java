package com.example.diplomski.so;

import java.util.ArrayList;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.PoslovniPartner;
import com.example.diplomski.entities.Racun;

public class PronadjiRacun extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		if(odo == null){
			return new ArrayList<Racun>();
		}
		PoslovniPartner pp = (PoslovniPartner) odo;
		
		return DbBroker.getInstance().pronadjiRacun(pp);
	}

}
